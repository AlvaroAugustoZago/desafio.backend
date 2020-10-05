package com.projuris.desafio.backend.os.domain;

import static java.util.Objects.requireNonNull;

import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.infra.domain.DomainEvent;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoAlterada;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoFinalizada;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoNova;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoPendente;
import com.projuris.desafio.backend.os.domain.excecao.OrdemServicoFinalizadaException;
import com.projuris.desafio.backend.os.domain.excecao.OrdemServicoNovaException;
import com.projuris.desafio.backend.os.domain.excecao.OrdemServicoPendenteException;

import lombok.NonNull;

public interface RegraTransicaoOrdemServico {

    RegraTransicaoOrdemServico assumir(@NonNull UsuarioId responsavelPor);

    RegraTransicaoOrdemServico alterar(@NonNull UsuarioId responsavelPor);
    
    RegraTransicaoOrdemServico transferir(@NonNull UsuarioId responsavelPor);
    
    RegraTransicaoOrdemServico finalizar(@NonNull UsuarioId responsavelPor);

    DomainEvent evento(OrdemServico os);

    static class Novo implements RegraTransicaoOrdemServico {

        @Override
        public RegraTransicaoOrdemServico assumir(@NonNull UsuarioId responsavelPor) {
            requireNonNull(responsavelPor);

            return SituacaoOrdemServico.pendente(responsavelPor);
        }

        @Override
        public RegraTransicaoOrdemServico alterar(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoNovaException();
        }
        
        @Override
        public RegraTransicaoOrdemServico transferir(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoNovaException();
        }

        @Override
        public RegraTransicaoOrdemServico finalizar(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoNovaException();
        }
        
        @Override
        public DomainEvent evento(OrdemServico os) {
            return OrdemServicoNova.apartirDa(os);
        }

    }

    static class Pendente implements RegraTransicaoOrdemServico {

        @Override
        public RegraTransicaoOrdemServico assumir(@NonNull UsuarioId responsavelPor) {
            requireNonNull(responsavelPor);

            return SituacaoOrdemServico.pendente(responsavelPor);
        }

        @Override
        public RegraTransicaoOrdemServico alterar(@NonNull UsuarioId responsavelPor) {
            requireNonNull(responsavelPor);

            return SituacaoOrdemServico.iniciada(responsavelPor);
        }
        
        @Override
        public RegraTransicaoOrdemServico transferir(@NonNull UsuarioId responsavelPor) {
            requireNonNull(responsavelPor);

            return SituacaoOrdemServico.pendente(responsavelPor);
        }

        @Override
        public RegraTransicaoOrdemServico finalizar(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoPendenteException();
        }
        
        @Override
        public DomainEvent evento(OrdemServico os) {
            return OrdemServicoPendente.apartirDa(os);
        }

    }
    
    static class Iniciada implements RegraTransicaoOrdemServico {

        @Override
        public RegraTransicaoOrdemServico assumir(@NonNull UsuarioId responsavelPor) {
            return null;
        }

        @Override
        public RegraTransicaoOrdemServico alterar(@NonNull UsuarioId responsavelPor) {
            requireNonNull(responsavelPor);

            return SituacaoOrdemServico.iniciada(responsavelPor);
        }

        @Override
        public RegraTransicaoOrdemServico transferir(@NonNull UsuarioId responsavelPor) {
            requireNonNull(responsavelPor);

            return SituacaoOrdemServico.iniciada(responsavelPor);
        }

        @Override
        public RegraTransicaoOrdemServico finalizar(@NonNull UsuarioId responsavelPor) {
            requireNonNull(responsavelPor);

            return SituacaoOrdemServico.finalizada(responsavelPor);
        }

        @Override
        public DomainEvent evento(OrdemServico os) {
            return OrdemServicoAlterada.from(os);
        }

    }
    
    static class Finalizada implements RegraTransicaoOrdemServico {

        @Override
        public RegraTransicaoOrdemServico assumir(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoFinalizadaException();
        }

        @Override
        public RegraTransicaoOrdemServico alterar(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoFinalizadaException();
        }

        @Override
        public RegraTransicaoOrdemServico transferir(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoFinalizadaException();
        }

        @Override
        public RegraTransicaoOrdemServico finalizar(@NonNull UsuarioId responsavelPor) {
            throw new OrdemServicoFinalizadaException();
        }

        @Override
        public DomainEvent evento(OrdemServico os) {
            return OrdemServicoFinalizada.from(os);
        }

    }

}
