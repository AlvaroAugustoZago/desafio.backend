package com.projuris.desafio.backend.infra.domain;

import static com.projuris.desafio.backend.infra.util.UUIDUtils.checkUUIDArgument;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Classe de base para objetos de valor que são utilizados como identificadores para {@link IdentifiableDomainObject}s.
 * Esses objetos são basicamente encapsuladores de {@link UUID}s.
 * 
 * @author Alvaro Augusto Zago
 *
 */
public abstract class DomainObjectId implements Serializable, ValueObject {

    private static final long serialVersionUID = 7556349653319120449L;

    @JsonValue
    private final String uuid;

    @JsonCreator
    protected DomainObjectId(@NonNull String uuid) {
        this.uuid = checkUUIDArgument(uuid);
    }

    /**
     * Retorna o {@link UUID} como uma {@link String}.
     */
    @NonNull
    public String toUUID() {
        return uuid;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || !getClass().equals(obj.getClass()))
            return false;

        return Objects.equals(uuid, ((DomainObjectId) obj).uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), uuid);
    }
}
