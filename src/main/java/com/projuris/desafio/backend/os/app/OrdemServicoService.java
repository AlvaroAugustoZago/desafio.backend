package com.projuris.desafio.backend.os.app;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.os.domain.OrdemServico;
import com.projuris.desafio.backend.os.domain.OrdemServicoId;
import com.projuris.desafio.backend.os.domain.OrdemServicoRepository;
import com.projuris.desafio.backend.os.domain.excecao.OrdemServicoNaoEncontradaException;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
@Validated
@Transactional
public class OrdemServicoService {
    
    private final OrdemServicoRepository repository;
    
    
    public OrdemServicoId handle(CriarOrdemServico cmd) {
        
        OrdemServico os = OrdemServico.builder()
            .cliente(cmd.cliente())
            .equipamento(cmd.equipamento())
            .problema(cmd.problema())
            .build();
        
        return repository.save(os).id();
    }

    public void handle(@Valid AssumirOrdemServico cmd) {
        OrdemServico os = repository.findById(cmd.id())
            .orElseThrow(OrdemServicoNaoEncontradaException::new);

        UsuarioId responsavel = recuperarUsuarioIdDoResponsavel(cmd.responsavel());

        os.assumir(responsavel);
        repository.save(os);

    }
    
    public void transferir(@Valid TransferirOrdemServico cmd) {
        OrdemServico os = repository.findById(cmd.id())
                .orElseThrow(OrdemServicoNaoEncontradaException::new);
        
        UsuarioId idResponsavel = recuperarUsuarioIdDoResponsavel(cmd.responsavel());
        
        if( idResponsavel.equals(os.situacaoAtual().responsavelPor())) {
            return;
        }
        
        os.transferir(idResponsavel);
        
        repository.save(os);
    }
    
    public void finalizar(@Valid FinalizarOrdemServico cmd) {
        OrdemServico os = repository.findById(cmd.id())
                .orElseThrow(OrdemServicoNaoEncontradaException::new);
        
        UsuarioId idResponsavel = recuperarUsuarioIdDoResponsavel(cmd.responsavel());
        
        os.finalizar(idResponsavel);
        
        repository.save(os);
    }
    
    private UsuarioId recuperarUsuarioIdDoResponsavel(Codigo responsavel) {
        return repository.recuperarUsuarioIdDoResponsavel(responsavel);
    }
}
