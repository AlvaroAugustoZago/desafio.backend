package com.projuris.desafio.backend.configurador.usuario.repositorio;

import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.infra.hibernate.DomainObjectIdCustomType;
import com.projuris.desafio.backend.infra.hibernate.DomainObjectIdTypeDescriptor;

public class UsuarioIdType extends DomainObjectIdCustomType<UsuarioId> {

    private static final long serialVersionUID = 5189229462190242562L;

    private static final DomainObjectIdTypeDescriptor<UsuarioId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
        UsuarioId.class, UsuarioId::from);

    public UsuarioIdType() {
        super(TYPE_DESCRIPTOR);
    }

}
