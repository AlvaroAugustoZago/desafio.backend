package com.projuris.desafio.backend.infra.hibernate;

class DummyIdType extends DomainObjectIdCustomType<DummyId> {

    private static final long serialVersionUID = -2279808385112967104L;

    private static final DomainObjectIdTypeDescriptor<DummyId> TYPE_DESCRIPTOR = new DomainObjectIdTypeDescriptor<>(
        DummyId.class, DummyId::new);

    public DummyIdType() {
        super(TYPE_DESCRIPTOR);
    }
}