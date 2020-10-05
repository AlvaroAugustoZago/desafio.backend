package com.projuris.desafio.backend.infra.domain;

import java.io.Serializable;

import org.springframework.lang.Nullable;

/**
 * Interface para os objetos de domínio que podem unicamente serem identificados.
 * 
 * @author Alvaro Augusto Zago
 *
 * @param <K> tipo do identificador.
 */
public interface IdentifiableDomainObject<K extends Serializable> extends DomainObject {

    /**
     * Retorna o identificador do objeto de domínio.
     *
     * @return o próprio identificador ou {@code null} caso ainda não possua identificador.
     */
    @Nullable
    K id();

}
