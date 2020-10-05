package com.projuris.desafio.backend.os.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

public interface OrdemServicoRepository {

    OrdemServico save(OrdemServico os);
    
    Optional<OrdemServico> findById(OrdemServicoId id);

    @Query("SELECT id FROM Usuario WHERE codigo = ?1")
    UsuarioId recuperarUsuarioIdDoResponsavel(Codigo codigo);
}
