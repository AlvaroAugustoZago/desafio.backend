package com.projuris.desafio.backend.os.app;

import com.projuris.desafio.backend.configurador.cliente.ClienteId;
import com.projuris.desafio.backend.configurador.equipamento.EquipamentoId;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriarOrdemServico {
    
    private final ClienteId cliente;
    private final EquipamentoId equipamento;
    private final String problema;
        
}
