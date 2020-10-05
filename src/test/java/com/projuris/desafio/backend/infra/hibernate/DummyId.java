package com.projuris.desafio.backend.infra.hibernate;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;

class DummyId extends DomainObjectId {

    private static final long serialVersionUID = -3375280756451793213L;

    protected DummyId(String uuid) {
        super(uuid);
    }

}
