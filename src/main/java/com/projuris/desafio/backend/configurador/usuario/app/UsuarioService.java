package com.projuris.desafio.backend.configurador.usuario.app;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.projuris.desafio.backend.configurador.usuario.dominio.Usuario;
import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@Validated
@Transactional
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioId handle(@Valid CriarUsuario cmd) {
        Usuario usuario = Usuario.builder()
            .nome(cmd.nome())
            .ativo(cmd.ativo())
            .codigo(cmd.codigo())
            .papeis(cmd.papeis())
            .build();

        return repository.save(usuario).id();
    }

}
