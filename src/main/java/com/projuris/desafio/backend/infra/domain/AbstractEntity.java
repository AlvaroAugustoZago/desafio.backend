package com.projuris.desafio.backend.infra.domain;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Definição básica de uma entidade.
 *
 * @param <K> o tipo do identificador da entidade.
 */
@MappedSuperclass

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
public abstract class AbstractEntity<K extends DomainObjectId> implements IdentifiableDomainObject<K> {

    @Id
    private K id;

    protected AbstractEntity(@NonNull K id) {
        this.id = requireNonNull(id, "O identificador não pode ser nulo.");
    }

}
