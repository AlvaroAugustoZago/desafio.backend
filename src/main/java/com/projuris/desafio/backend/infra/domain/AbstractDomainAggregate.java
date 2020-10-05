package com.projuris.desafio.backend.infra.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.MappedSuperclass;

import lombok.NoArgsConstructor;

/**
 * Definição básica de um agregado.
 *
 * @param <K> o tipo do identificador do agregado.
 */
@MappedSuperclass
@NoArgsConstructor(access = PROTECTED)
public abstract class AbstractDomainAggregate<K extends DomainObjectId> extends AbstractDomainEntity<K> {

    protected AbstractDomainAggregate(K id) {
        super(id);
    }

}
