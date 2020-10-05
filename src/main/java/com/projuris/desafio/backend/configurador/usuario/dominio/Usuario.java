package com.projuris.desafio.backend.configurador.usuario.dominio;

import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;

import com.projuris.desafio.backend.configurador.usuario.dominio.evento.UsuarioCriado;
import com.projuris.desafio.backend.infra.domain.AbstractDomainEntity;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PRIVATE, force = true)

@Entity
public final class Usuario extends AbstractDomainEntity<UsuarioId> {

    private final String nome;

    private final Instant criadoEm;

    private final boolean ativo;

    private final Codigo codigo;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Papel.class, fetch = LAZY)
    @CollectionTable(name = "usuario_papel", joinColumns = @JoinColumn(name = "usuario"))
    @Column(name = "papel")
    private final Set<Papel> papeis;

    private LocalDateTime alteradoEm;

    /**
     * Para uso exclusivo do {@link Builder}.
     * 
     * @deprecated
     */
    @Builder
    @Deprecated(forRemoval = false, since = "0")
    @SuppressWarnings("all")
    protected Usuario(String nome, boolean ativo, Codigo codigo, Set<Papel> papeis) {
        throw new IllegalStateException();
    }

    private Usuario(UsuarioBuilder builder) {
        super(requireNonNull(builder.id));
        this.nome = requireNonNull(builder.nome);
        this.ativo = builder.ativo;
        this.codigo = requireNonNull(builder.codigo);
        this.criadoEm = requireNonNull(builder.criadoEm);
        this.papeis = requireNonNull(builder.papeis);
    }

    public Set<Papel> papeis() {
        return unmodifiableSet(papeis);
    }

    public static class UsuarioBuilder {

        private UsuarioId id;

        private Instant criadoEm;

        public Usuario build() {

            id = UsuarioId.novo();
            criadoEm = Instant.now();

            if (isNull(papeis))
                papeis = emptySet();

            Usuario usuario = new Usuario(this);
            usuario.registrarEvento(UsuarioCriado.from(usuario));

            return usuario;
        }
    }
}