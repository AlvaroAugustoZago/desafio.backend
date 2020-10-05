package com.projuris.desafio.backend.configurador.usuario.dominio.evento;

import static java.time.Instant.now;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.Set;

import com.projuris.desafio.backend.configurador.usuario.dominio.Papel;
import com.projuris.desafio.backend.configurador.usuario.dominio.Usuario;
import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.infra.domain.DomainEvent;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(access = PRIVATE)
public class UsuarioCriado implements DomainEvent {

    private final UsuarioId id;

    private final String nome;

    private final boolean ativo;

    private final Codigo codigo;

    private final Set<Papel> papeis;

    private final Instant ocorridoEm;

    public static UsuarioCriado from(Usuario usuario) {
        return UsuarioCriado.builder()
            .id(usuario.id())
            .nome(usuario.nome())
            .ativo(usuario.ativo())
            .codigo(usuario.codigo())
            .papeis(usuario.papeis())
            .ocorridoEm(now())
            .build();
    }

}
