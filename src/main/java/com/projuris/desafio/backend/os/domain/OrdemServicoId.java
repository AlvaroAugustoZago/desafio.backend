package com.projuris.desafio.backend.os.domain;

import java.util.UUID;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;

public class OrdemServicoId extends DomainObjectId {

    private static final long serialVersionUID = -5187358927773463296L;

    protected OrdemServicoId(String uuid) {
        super(uuid);
    }

    public static OrdemServicoId novo() {
        return new OrdemServicoId(UUID.randomUUID().toString());
    }

    public static OrdemServicoId from(String uuid) {
        return new OrdemServicoId(uuid);
    }
}
