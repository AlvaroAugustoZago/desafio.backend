package com.projuris.desafio.backend.configurador.usuario.app;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projuris.desafio.backend.configurador.usuario.dominio.Papel;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriarUsuario {

    @NotNull(message = "{CriarUsuario.nome.NotNull}")
    @NotBlank(message = "{CriarUsuario.nome.NotBlank}")
    @Size(min = 1, max = 150, message = "{CriarUsuario.nome.Size}")
    private final String nome;

    private final boolean ativo;

    @Valid
    @NotNull(message = "{CriarUsuario.codigo.NotNull}")
    private final Codigo codigo;

    private Set<Papel> papeis;

}
