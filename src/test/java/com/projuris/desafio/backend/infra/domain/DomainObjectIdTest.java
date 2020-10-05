package com.projuris.desafio.backend.infra.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("Teste de identificadores de dominio")
public class DomainObjectIdTest {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final String uuid = "2a6c919b-d7ff-49c1-ae69-cccfd2279acd";

    private static final DummyId id = new DummyId(uuid);

    static class DummyId extends DomainObjectId {

        private static final long serialVersionUID = -3375280756451793213L;

        protected DummyId(String uuid) {
            super(uuid);
        }
    }

    static class OtherDummyId extends DomainObjectId {

        private static final long serialVersionUID = 2611086522034354972L;

        protected OtherDummyId(String uuid) {
            super(uuid);
        }
    }

    @Test
    @DisplayName("Inv\u00E1lido")
    void invalido() {
        assertThrows(IllegalArgumentException.class, () -> new DummyId(""));
    }

    @Test
    @DisplayName("Para UUID")
    void idToUUID() {
        assertEquals(uuid, id.toUUID());
    }

    @Test
    @DisplayName("Para string")
    void idToString() {
        assertEquals(String.format("DummyId[%s]", id.toUUID()), id.toString());
    }

    @Test
    @DisplayName("Hash code")
    void idHashCode() {
        assertEquals(id.hashCode(), new DummyId(uuid).hashCode());
    }

    @Test
    @DisplayName("Equalidade")
    void idEquals() {

        DummyId other = new DummyId(uuid);
        DomainObjectId otherOne = new OtherDummyId(uuid);

        assertTrue(id.equals(id));
        assertTrue(id.equals(other));
        assertFalse(id.equals(otherOne));
        assertFalse(id.equals(null));
    }

    @Test
    @DisplayName("Para json")
    void toJson() throws Exception {
        assertEquals("\"" + id.toUUID() + "\"", mapper.writeValueAsString(id));
    }

    @Test
    @DisplayName("De json")
    void fromJson() throws Exception {
        assertEquals(id, mapper.readValue("\"" + uuid + "\"", DummyId.class));
    }
}
