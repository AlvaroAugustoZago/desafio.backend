package com.projuris.desafio.backend.configurador.usuario.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projuris.desafio.backend.configurador.usuario.dominio.Usuario;
import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioRepository;

@Repository
public interface UsuarioRepositoryJpa extends UsuarioRepository, CrudRepository<Usuario, UsuarioId> {
}
