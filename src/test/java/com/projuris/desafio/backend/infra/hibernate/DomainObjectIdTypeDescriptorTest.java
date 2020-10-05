package com.projuris.desafio.backend.infra.hibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hibernate.HibernateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;


@DisplayName("Descritor do tipo customizado de identificadores de dominio para hibernate")
public class DomainObjectIdTypeDescriptorTest {

    private static final String uuid = "2a6c919b-d7ff-49c1-ae69-cccfd2279acd";

    private static final DummyId id = new DummyId(uuid);

    private static final DomainObjectIdTypeDescriptor<DummyId> descriptor = new DomainObjectIdTypeDescriptor<>(
        DummyId.class, DummyId::new);

    static class OtherDummyId extends DomainObjectId {

        private static final long serialVersionUID = 7952870945141734452L;

        protected OtherDummyId() {
            super(uuid);
        }
    }

    @Test
    @DisplayName("Para string")
    void string() {
        assertEquals(uuid, descriptor.toString(id));
    }

    @Test
    @DisplayName("De string")
    void fromString() {
        assertEquals(id, descriptor.fromString(uuid));
    }

    @Nested
    @DisplayName("Desempacotar")
    class Unwrap {

        @Test
        @DisplayName("Nulo")
        void unwrapNull() {
            assertNull(descriptor.unwrap(null, null, null));
        }

        @Test
        @DisplayName("Tipo pr\u00E9 definido")
        void unwrapJavaType() {
            assertEquals(id, descriptor.unwrap(id, DummyId.class, null));
        }

        @Test
        @DisplayName("Como string")
        void unwrapToString() {
            assertEquals(uuid, descriptor.unwrap(id, String.class, null));
        }

        @Test
        @DisplayName("Inv\u00E1lido")
        void unwrapException() {
            assertThrows(HibernateException.class, () -> descriptor.unwrap(id, OtherDummyId.class, null));
        }
    }

    @Nested
    @DisplayName("Empacotar")
    class wrap {

        @Test
        @DisplayName("Nulo")
        void unwrapNull() {
            assertNull(descriptor.wrap(null, null));
        }

        @Test
        @DisplayName("Tipo pr\u00E9 definido")
        void unwrapJavaType() {
            assertEquals(id, descriptor.wrap(id, null));
        }

        @Test
        @DisplayName("Como string")
        void unwrapToString() {
            assertEquals(id, descriptor.wrap(uuid, null));
        }

        @Test
        @DisplayName("Inv\u00E1lido")
        void unwrapException() {
            assertThrows(HibernateException.class, () -> descriptor.wrap(new OtherDummyId(), null));
        }
    }
}
