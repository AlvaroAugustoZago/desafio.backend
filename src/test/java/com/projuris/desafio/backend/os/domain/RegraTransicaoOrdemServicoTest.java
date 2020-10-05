package com.projuris.desafio.backend.os.domain;

import static com.projuris.desafio.backend.os.domain.SituacaoOrdemServico.iniciada;
import static com.projuris.desafio.backend.os.domain.SituacaoOrdemServico.pendente;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.projuris.desafio.backend.configurador.cliente.ClienteId;
import com.projuris.desafio.backend.configurador.equipamento.EquipamentoId;
import com.projuris.desafio.backend.configurador.usuario.UsuarioId;
import com.projuris.desafio.backend.os.domain.OrdemServico.OrdemServicoBuilder;
import com.projuris.desafio.backend.os.domain.excecao.OrdemServicoNovaException;

@DisplayName("Regra de transi\u00E7\u00E3o da Ordem Servico")
public class RegraTransicaoOrdemServicoTest {
    
    private static OrdemServicoBuilder builder;

    @BeforeAll
    static void beforeAll() {
        builder = OrdemServico.builder()
                          .cliente(ClienteId.novo())
                          .equipamento(EquipamentoId.novo())
                          .problema("Teste");
    }

    @Nested
    @DisplayName("Novo")
    class Novo {
        private RegraTransicaoOrdemServico regra = new RegraTransicaoOrdemServico.Novo();

        @Test
        @DisplayName("Assumir inspe\u00E7\u00E3o inv\u00E1lido")
        void assumir() {
            assertThrows(NullPointerException.class, () -> regra.assumir(null));
        }
        
        @Test
        @DisplayName("Assumir inspe\u00E7\u00E3o v\u00E1lido")
        void assumirInspecaoValido() {
            UsuarioId responsavelPor = UsuarioId.novo();
            OrdemServico os = builder.build();

            os.assumir(responsavelPor);
            assertEquals(pendente(responsavelPor), regra.assumir(responsavelPor));
        }
        
        @Test
        @DisplayName("Alterar inspe\u00E7\u00E3o nova")
        void alterarInspecaoNova() {
            UsuarioId responsavelPor = UsuarioId.novo();
            OrdemServico inspecao = builder.build();
            
            assertThrows(OrdemServicoNovaException.class, () -> inspecao.alterar(responsavelPor));
        }
    }
    
    @Nested
    @DisplayName("Pendente")
    class Pendente {
        private RegraTransicaoOrdemServico regra = new RegraTransicaoOrdemServico.Pendente();
        
        @Test
        @DisplayName("Assumir inspe\u00E7\u00E3o pendente")
        void assumir() {
            UsuarioId responsavelPor = UsuarioId.novo();
            UsuarioId novoResponsavelPor = UsuarioId.novo();
            OrdemServico inspecao = builder.build();

            inspecao.assumir(responsavelPor);
            
            inspecao.assumir(novoResponsavelPor);
            
            assertEquals(pendente(responsavelPor), regra.assumir(responsavelPor));
            assertEquals(pendente(novoResponsavelPor), inspecao.situacaoAtual());
        }

        @Test
        @DisplayName("Alterar inspe\u00E7\u00E3o pendente")
        void alterarInspecaoPendente() throws InterruptedException {
            UsuarioId responsavelPor = UsuarioId.novo();
            OrdemServico inspecao = builder.build();
            
            inspecao.assumir(responsavelPor);
            
            inspecao.alterar(responsavelPor);
            
            assertEquals(iniciada(responsavelPor), regra.alterar(responsavelPor));
            assertEquals(iniciada(responsavelPor), inspecao.situacaoAtual());
        }

    }

}
