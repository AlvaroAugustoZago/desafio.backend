package com.projuris.desafio.backend.os.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.projuris.desafio.backend.configurador.usuario.UsuarioId;
import com.projuris.desafio.backend.os.domain.SituacaoOrdemServico.Status;

@DisplayName("Testes das Situa\u00E7\u00F5es da Ordem Servico")
public class SituacaoOrdemServicoTest {
    
    @Test
    @DisplayName("Novo")
    void novo() {
        SituacaoOrdemServico situacaoInspecao = SituacaoOrdemServico.nova();
        assertNotNull(situacaoInspecao);
        assertEquals(Status.NOVA, situacaoInspecao.status());
        assertNull(situacaoInspecao.responsavelPor());
        assertTrue(situacaoInspecao.ocorridoEm().isBefore(Instant.now().plusSeconds(1)));
    }
    
    @Test
    @DisplayName("Assumir")
    void assumir() {
        UsuarioId responsavelPor = UsuarioId.novo();
        
        SituacaoOrdemServico situacaoInspecao = SituacaoOrdemServico.pendente(responsavelPor);
        assertNotNull(situacaoInspecao);
        assertEquals(Status.PENDENTE, situacaoInspecao.status());
        assertEquals(responsavelPor.toString(), situacaoInspecao.responsavelPor().toString());
        assertTrue(situacaoInspecao.ocorridoEm().isBefore(Instant.now().plusSeconds(1)));
    }
    
    @Test
    @DisplayName("Alterar")
    void alterar() {
        UsuarioId responsavelPor = UsuarioId.novo();
        
        SituacaoOrdemServico situacaoInspecao = SituacaoOrdemServico.iniciada(responsavelPor);
        assertNotNull(situacaoInspecao);
        assertEquals(Status.INICIADA, situacaoInspecao.status());
        assertEquals(responsavelPor.toString(), situacaoInspecao.responsavelPor().toString());
        assertTrue(situacaoInspecao.ocorridoEm().isBefore(Instant.now().plusSeconds(1)));
    }
}
