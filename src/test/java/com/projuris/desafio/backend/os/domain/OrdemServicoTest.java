package com.projuris.desafio.backend.os.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.projuris.desafio.backend.configurador.cliente.ClienteId;
import com.projuris.desafio.backend.configurador.equipamento.EquipamentoId;

@DisplayName("Dom\u00EDnio de Ordem de Servi\u00E7o")
public class OrdemServicoTest {
    
    @Test
    @DisplayName("Novo")
    void novo() {

        EquipamentoId equipamento = EquipamentoId.novo();
        ClienteId cliente = ClienteId.novo();
        
        OrdemServico os = OrdemServico.builder()
            .cliente(cliente)
            .equipamento(equipamento)
            .problema("")
            .build();

        assertNotNull(os);
        assertNotNull(os.id());
        
        assertEquals(cliente, os.cliente());
        assertEquals(equipamento, os.equipamento());
        assertEquals("", os.problema());
        assertNotNull(os.situacoes());
        assertTrue(os.situacoes().contains(SituacaoOrdemServico.nova()));
    }
}
