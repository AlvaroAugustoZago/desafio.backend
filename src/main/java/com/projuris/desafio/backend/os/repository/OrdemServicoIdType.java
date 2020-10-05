package com.projuris.desafio.backend.os.repository;

import com.projuris.desafio.backend.infra.hibernate.DomainObjectIdCustomType;
import com.projuris.desafio.backend.infra.hibernate.DomainObjectIdTypeDescriptor;
import com.projuris.desafio.backend.os.domain.OrdemServicoId;

public class OrdemServicoIdType extends DomainObjectIdCustomType<OrdemServicoId> {
    private static final long serialVersionUID = -7134936615050740789L;


    private static final DomainObjectIdTypeDescriptor<OrdemServicoId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
        OrdemServicoId.class, OrdemServicoId::from);

    public OrdemServicoIdType() {
        super(TYPE_DESCRIPTOR);
    }

}