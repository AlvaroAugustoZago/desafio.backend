package com.projuris.desafio.backend.os.domain.evento;

import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

import com.projuris.desafio.backend.infra.domain.DomainEvent;
import com.projuris.desafio.backend.os.domain.OrdemServico;
import com.projuris.desafio.backend.os.domain.OrdemServicoId;
import com.projuris.desafio.backend.os.domain.SituacaoOrdemServico;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(access = PRIVATE)
public class OrdemServicoPendente implements DomainEvent {

    private Instant ocorridoEm;

    private OrdemServicoId id;

    private SituacaoOrdemServico status;


    public static OrdemServicoPendente apartirDa(OrdemServico os) {
        return OrdemServicoPendente.builder()
                .id(os.id())
                .ocorridoEm(Instant.now())
                .status(os.situacaoAtual())
                .build();
    }

}
