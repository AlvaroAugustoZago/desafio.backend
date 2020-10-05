package com.projuris.desafio.backend.configurador.usuario.dominio;

import java.util.UUID;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;

public class UsuarioId extends DomainObjectId {

    private static final long serialVersionUID = -6565017815185343325L;

    private UsuarioId(String uuid) {
        super(uuid);
    }

    public static UsuarioId novo() {
        return new UsuarioId(UUID.randomUUID().toString());
    }

    public static UsuarioId from(String uuid) {
        return new UsuarioId(uuid);
    }

}
