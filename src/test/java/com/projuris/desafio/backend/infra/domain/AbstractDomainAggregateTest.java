package com.projuris.desafio.backend.infra.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.Data;
import lombok.EqualsAndHashCode;

@DisplayName("Teste de entidades de dominio")
public class AbstractDomainAggregateTest {

    private static final AggregadoId id = new AggregadoId("2a6c919b-d7ff-49c1-ae69-cccfd2279acd");

    static class AggregadoId extends DomainObjectId {

        private static final long serialVersionUID = 7749199316701627164L;

        protected AggregadoId(String uuid) {
            super(uuid);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    static class Aggregado extends AbstractDomainAggregate<AggregadoId> {

        private Aggregado(AggregadoId id) {
            super(id);
            registrarEvento(new DomainEvent() {
                @Override
                public Instant ocorridoEm() {
                    return null;
                }
            });
        }
    }

    @Test
    @DisplayName("N\u00E3o registra evento nulo")
    void registrarEventoInvalido() {
        Aggregado entidade = new Aggregado(id);
        assertThrows(NullPointerException.class, () -> entidade.registrarEvento(null));
    }

    @Test
    @DisplayName("N\u00E3o registra ao realizar opera\u00E7\u00E3o")
    void registrarEvento() {
        Aggregado entidade = new Aggregado(id);
        assertFalse(entidade.eventos().isEmpty());
        assertEquals(1, entidade.eventos().size());
    }

    @Test
    @DisplayName("Remove eventos registrados")
    void removerEventos() {

        Aggregado entidade = new Aggregado(id);
        entidade.removerEventos();

        assertTrue(entidade.eventos().isEmpty());
    }

    @Test
    @DisplayName("Identificador")
    void id() {
        assertEquals(id, new Aggregado(id).id());
    }

    @Test
    @DisplayName("Identificador nulo")
    void idNulo() {
        assertThrows(NullPointerException.class, () -> new Aggregado(null));
    }

}
