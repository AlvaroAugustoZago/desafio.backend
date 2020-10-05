package com.projuris.desafio.backend.os.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import com.projuris.desafio.backend.configurador.cliente.ClienteId;
import com.projuris.desafio.backend.configurador.equipamento.EquipamentoId;
import com.projuris.desafio.backend.configurador.usuario.UsuarioId;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoAlterada;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoFinalizada;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoNova;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoPendente;

@DisplayName("Eventos da OrdemServico")
public class OrdemServicoEventosTest {

    @ParameterizedTest
    @ArgumentsSource(OrdemServicoProvider.class)
    @DisplayName("Novo")
    void criado(OrdemServico os) {

        OrdemServicoNova evento = OrdemServicoNova.apartirDa(os);

        assertNotNull(evento);

        assertEquals(os.id(), evento.id());
        assertEquals(os.cliente(), evento.cliente());
        assertEquals(os.equipamento(), evento.equipamento());
        assertEquals(os.problema(), evento.problema());

        assertTrue(evento.ocorridoEm().isBefore(Instant.now().plusSeconds(1l)));
    }

    @ParameterizedTest
    @ArgumentsSource(OrdemServicoProvider.class)
    @DisplayName("Pendente")
    void pendente(OrdemServico os) {
        os.assumir(UsuarioId.novo());

        OrdemServicoPendente evento = OrdemServicoPendente.apartirDa(os);

        assertNotNull(evento);
        assertEquals(os.id(), evento.id());
        assertEquals(SituacaoOrdemServico.Status.PENDENTE, evento.status().status());
        assertEquals(os.situacaoAtual().status(), evento.status().status());
    }

    @ParameterizedTest
    @ArgumentsSource(OrdemServicoProvider.class)
    @DisplayName("Alterada")
    void alterada(OrdemServico os) {
        OrdemServicoAlterada evento = OrdemServicoAlterada.from(os);

        os.assumir(UsuarioId.novo());
        os.alterar(UsuarioId.novo());

        assertNotNull(evento);
        assertEquals(os.id(), evento.os().id());
        assertEquals(SituacaoOrdemServico.Status.INICIADA, evento.os().situacaoAtual().status());
        assertEquals(os.situacaoAtual().status(), evento.os().situacaoAtual().status());
    }

    @ParameterizedTest
    @ArgumentsSource(OrdemServicoProvider.class)
    @DisplayName("Finalizada")
    void finalizada(OrdemServico os) {
        os.assumir(UsuarioId.novo());
        os.alterar(UsuarioId.novo());
        os.finalizar(UsuarioId.novo());

        OrdemServicoFinalizada evento = OrdemServicoFinalizada.from(os);

        assertNotNull(evento);
        assertEquals(os.id(), evento.os());
        assertEquals(SituacaoOrdemServico.Status.FINALIZADA, evento.status().status());
        assertEquals(os.situacaoAtual().status(), evento.status().status());
    }

    static class OrdemServicoProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

            return Stream.of(
                OrdemServico.builder()
                    .cliente(ClienteId.novo())
                    .equipamento(EquipamentoId.novo())
                    .problema("Teste Status")
                    .build())
                .map(Arguments::of);
        }
    }
}
