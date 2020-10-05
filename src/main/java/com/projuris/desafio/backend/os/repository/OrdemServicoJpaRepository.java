package com.projuris.desafio.backend.os.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projuris.desafio.backend.os.domain.OrdemServico;
import com.projuris.desafio.backend.os.domain.OrdemServicoId;
import com.projuris.desafio.backend.os.domain.OrdemServicoRepository;

@Repository
public interface OrdemServicoJpaRepository extends OrdemServicoRepository, CrudRepository<OrdemServico, OrdemServicoId> {

}