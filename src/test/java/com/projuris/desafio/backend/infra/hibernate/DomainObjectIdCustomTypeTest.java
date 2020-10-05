package com.projuris.desafio.backend.infra.hibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tipo customizado de identificadores de dominio para hibernate")
public class DomainObjectIdCustomTypeTest {

    private static final String uuid = "2a6c919b-d7ff-49c1-ae69-cccfd2279acd";

    private static final DummyId id = new DummyId(uuid);

    private static final DummyIdType type = new DummyIdType();

    @Test
    @DisplayName("Nome do objeto")
    void name() {
        assertEquals("DummyId", type.getName());
    }

    @Test
    @DisplayName("Cira\u00E7\u00E3o do identificador")
    void from() {
        assertEquals(id, type.from(uuid));
    }

}
