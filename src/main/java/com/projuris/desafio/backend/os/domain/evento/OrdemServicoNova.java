package com.projuris.desafio.backend.os.domain.evento;

import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

import com.projuris.desafio.backend.configurador.cliente.ClienteId;
import com.projuris.desafio.backend.configurador.equipamento.EquipamentoId;
import com.projuris.desafio.backend.infra.domain.DomainEvent;
import com.projuris.desafio.backend.os.domain.OrdemServico;
import com.projuris.desafio.backend.os.domain.OrdemServicoId;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(access = PRIVATE)
public class OrdemServicoNova implements DomainEvent {

    private Instant ocorridoEm;
    private final OrdemServicoId id;
    private final ClienteId cliente;
    private final EquipamentoId equipamento;
    private final String problema;

    public static OrdemServicoNova apartirDa(OrdemServico os) {
        return OrdemServicoNova.builder()
            .id(os.id())
            .cliente(os.cliente())
            .equipamento(os.equipamento())
            .problema(os.problema())
            .ocorridoEm(Instant.now())
            .build();
    }
}
