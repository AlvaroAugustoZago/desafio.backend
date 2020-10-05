package com.projuris.desafio.backend.os.app;

import com.projuris.desafio.backend.os.domain.OrdemServicoId;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class FinalizarOrdemServico {

    private OrdemServicoId id;
    private Codigo responsavel;

}
