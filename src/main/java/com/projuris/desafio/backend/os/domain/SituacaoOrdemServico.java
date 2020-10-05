package com.projuris.desafio.backend.os.domain;

import static java.time.Instant.now;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.infra.domain.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(exclude = { "ocorridoEm" })
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE, force = true)

@Embeddable
public class SituacaoOrdemServico implements RegraTransicaoOrdemServico {

    private final Instant ocorridoEm;

    @Enumerated(EnumType.STRING)
    private final Status status;

    private final UsuarioId responsavelPor;
    
    public static SituacaoOrdemServico nova() {
        return new SituacaoOrdemServico(now(), Status.NOVA, null);
    }

    public static SituacaoOrdemServico pendente(UsuarioId responsavelPor) {
        return new SituacaoOrdemServico(now(), Status.PENDENTE, responsavelPor);
    }

    public static SituacaoOrdemServico iniciada(UsuarioId responsavelPor) {
        return new SituacaoOrdemServico(now(), Status.INICIADA, responsavelPor);
    }
    
    public static SituacaoOrdemServico finalizada(UsuarioId responsavelPor) {
        return new SituacaoOrdemServico(now(), Status.FINALIZADA, responsavelPor);
    }
    
    @Override
    public RegraTransicaoOrdemServico assumir(UsuarioId responsavelPor) {
        return status.assumir(responsavelPor);
    }
    
    @Override
    public RegraTransicaoOrdemServico transferir(UsuarioId responsavelPor) {
        return status.transferir(responsavelPor);
    }

    @Override
    public RegraTransicaoOrdemServico alterar(UsuarioId responsavelPor) {
        return status.alterar(responsavelPor);
    }
    
    @Override
    public RegraTransicaoOrdemServico finalizar(@NonNull UsuarioId responsavelPor) {
        return status.finalizar(responsavelPor);
    }
    
    @Override
    public DomainEvent evento(OrdemServico os) {
        return status.evento(os);
    }

    
    @AllArgsConstructor(access = PRIVATE)
    @NoArgsConstructor(access = PRIVATE, force = true)
    public enum Status implements RegraTransicaoOrdemServico {
        NOVA(new RegraTransicaoOrdemServico.Novo()),
        INICIADA(new RegraTransicaoOrdemServico.Iniciada()),
        PENDENTE(new RegraTransicaoOrdemServico.Pendente()),
        FINALIZADA(new RegraTransicaoOrdemServico.Finalizada());

        RegraTransicaoOrdemServico regra;

        @Override
        public RegraTransicaoOrdemServico assumir(@NonNull UsuarioId responsavelPor) {
            return regra.assumir(responsavelPor);
        }

        @Override
        public RegraTransicaoOrdemServico alterar(@NonNull UsuarioId responsavelPor) {
            return regra.alterar(responsavelPor);
        }

        @Override
        public RegraTransicaoOrdemServico transferir(@NonNull UsuarioId responsavelPor) {
            return regra.transferir(responsavelPor);
        }
        
        @Override
        public RegraTransicaoOrdemServico finalizar(@NonNull UsuarioId responsavelPor) {
            return regra.finalizar(responsavelPor);
        }

        @Override
        public DomainEvent evento(OrdemServico os) {
            return regra.evento(os);
        }
        

    }
}
