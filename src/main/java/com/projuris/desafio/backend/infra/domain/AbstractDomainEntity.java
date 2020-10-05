package com.projuris.desafio.backend.infra.domain;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Definição básica de uma entidade.
 *
 * @param <K> o tipo do identificador da entidade.
 */
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public abstract class AbstractDomainEntity<K extends DomainObjectId> extends AbstractEntity<K> {

    @Transient
    @JsonIgnore
    private List<DomainEvent> eventosDominio = new ArrayList<>();

    protected AbstractDomainEntity(K id) {
        super(id);
    }

    /**
     * Realiza o registro dos eventos a serem publicados quando for realizada a persistência.
     *
     * @param event o evento a ser registrado.
     */
    protected void registrarEvento(@NonNull DomainEvent event) {
        requireNonNull(event, "Evento não pode ser nulo.");
        this.eventosDominio.add(event);
    }

    /**
     * Função chamada pelo framework de persistência para apagar os eventos que foram registrados e já foram publicados.
     */
    @AfterDomainEventPublication
    protected void removerEventos() {
        this.eventosDominio.clear();
    }

    /**
     * Retorna todos os eventos que foram registrados para publicação.
     */
    @DomainEvents
    public Collection<DomainEvent> eventos() {
        return unmodifiableList(eventosDominio);
    }

}
