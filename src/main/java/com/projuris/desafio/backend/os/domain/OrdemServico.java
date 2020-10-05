package com.projuris.desafio.backend.os.domain;

import static com.projuris.desafio.backend.os.domain.SituacaoOrdemServico.nova;
import static java.util.Objects.requireNonNull;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;

import org.hibernate.annotations.DynamicUpdate;

import com.projuris.desafio.backend.configurador.cliente.ClienteId;
import com.projuris.desafio.backend.configurador.equipamento.EquipamentoId;
import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;
import com.projuris.desafio.backend.infra.domain.AbstractDomainAggregate;
import com.projuris.desafio.backend.os.domain.evento.OrdemServicoNova;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE, force = true)

@Entity
@DynamicUpdate
public final class OrdemServico extends AbstractDomainAggregate<OrdemServicoId> {
    private final ClienteId cliente;
    private final EquipamentoId equipamento;
    private final String problema;
    
    @OrderBy("ocorridoEm ASC")
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "ordem_servico_situacao", joinColumns = @JoinColumn(name = "ordem_servico"))
    private List<SituacaoOrdemServico> situacoes;
    
    private OrdemServico(OrdemServicoBuilder builder) {
        super(requireNonNull(builder.id));
        this.cliente = requireNonNull(builder.cliente);
        this.equipamento = requireNonNull(builder.equipamento);
        this.problema = requireNonNull(builder.problema);
        this.situacoes = requireNonNull(builder.situacoes);
    }
    
    public static class OrdemServicoBuilder {

        private OrdemServicoId id;

        private ArrayList<SituacaoOrdemServico> situacoes = new ArrayList<SituacaoOrdemServico>();

        
        public OrdemServico build() {

            id = OrdemServicoId.novo();

            situacoes.clear();
            situacoes.add(nova());
            
            OrdemServico os = new OrdemServico(this);
            os.registrarEvento(OrdemServicoNova.apartirDa(os));

            return os;
        }
    }

    public SituacaoOrdemServico situacaoAtual() {
        return situacoes.get(situacoes.size() - 1);
    }
    
    public void assumir(UsuarioId responsavel) {
        situacoes.add((SituacaoOrdemServico) situacaoAtual().assumir(responsavel));
        this.registrarEvento(situacaoAtual().evento(this));
    }

    public void transferir(UsuarioId responsavel) {
        situacoes.add((SituacaoOrdemServico) situacaoAtual().transferir(responsavel));
    }

    public void alterar(UsuarioId responsavel) {
        situacoes.add((SituacaoOrdemServico) situacaoAtual().alterar(responsavel));
        this.registrarEvento(situacaoAtual().evento(this));
    }

    public void finalizar(UsuarioId responsavel) {
        situacoes.add((SituacaoOrdemServico) situacaoAtual().finalizar(responsavel));
        this.registrarEvento(situacaoAtual().evento(this));
    }
}
