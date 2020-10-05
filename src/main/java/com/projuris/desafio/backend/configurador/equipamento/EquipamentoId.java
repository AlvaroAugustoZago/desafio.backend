package com.projuris.desafio.backend.configurador.equipamento;

import java.util.UUID;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;

public class EquipamentoId extends DomainObjectId {

    private static final long serialVersionUID = 3535173026095981591L;

    private EquipamentoId(String uuid) {
        super(uuid);
    }

    public static EquipamentoId from(String uuid) {
        return new EquipamentoId(uuid);
    }

    public static EquipamentoId novo() {
        return new EquipamentoId(UUID.randomUUID().toString());
    }

}
