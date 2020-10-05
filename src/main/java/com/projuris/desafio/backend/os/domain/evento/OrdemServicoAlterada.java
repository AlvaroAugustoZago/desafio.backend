package com.projuris.desafio.backend.os.domain.evento;

import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

import com.projuris.desafio.backend.infra.domain.DomainEvent;
import com.projuris.desafio.backend.os.domain.OrdemServico;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(access = PRIVATE)
public class OrdemServicoAlterada implements DomainEvent {

    private Instant ocorridoEm;

    private OrdemServico os;

    public static OrdemServicoAlterada from(OrdemServico os) {
        return OrdemServicoAlterada.builder()
                .ocorridoEm(Instant.now())
                .os(os)
                .build();
    }
}
