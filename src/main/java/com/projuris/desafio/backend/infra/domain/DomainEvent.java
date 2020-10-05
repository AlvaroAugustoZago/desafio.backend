package com.projuris.desafio.backend.infra.domain;

import java.time.Instant;

import org.springframework.lang.NonNull;

/**
 * Interface de marcação para os eventos de domínio.
 * 
 * @author Thiago A. de Souza Weber
 *
 */
public interface DomainEvent extends DomainObject {

    /**
     * Momento em que ocorreu o fato.
     */
    @NonNull
    Instant ocorridoEm();

}
