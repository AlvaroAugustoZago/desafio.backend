package com.projuris.desafio.backend.configurador.cliente;

import java.util.UUID;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;

public class ClienteId extends DomainObjectId {

    private ClienteId(String uuid) {
        super(uuid);
    }

    public static ClienteId from(String uuid) {
        return new ClienteId(uuid);
    }

    public static ClienteId novo() {
        return new ClienteId(UUID.randomUUID().toString());
    }

}
