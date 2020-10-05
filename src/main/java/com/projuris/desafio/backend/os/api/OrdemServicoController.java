package com.projuris.desafio.backend.os.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projuris.desafio.backend.os.app.AssumirOrdemServico;
import com.projuris.desafio.backend.os.app.CriarOrdemServico;
import com.projuris.desafio.backend.os.app.FinalizarOrdemServico;
import com.projuris.desafio.backend.os.app.OrdemServicoService;
import com.projuris.desafio.backend.os.app.TransferirOrdemServico;
import com.projuris.desafio.backend.os.domain.OrdemServicoId;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping(path = OrdemServicoController.PATH, produces = APPLICATION_JSON_VALUE)
public class OrdemServicoController {

    public static final String PATH = "/api/v1/ordem-servico/"; //NOSONAR

    private final OrdemServicoService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody CriarOrdemServico cmd) {

        OrdemServicoId id = service.handle(cmd);

        return ResponseEntity.created(fromCurrentRequest()
            .path("/").path(id.toUUID()).build().toUri())
            .build();
    }
    
    @PutMapping(path = "{id}/assumir", consumes = APPLICATION_JSON_VALUE)
    public void assumir(@PathVariable OrdemServicoId id) {

        Codigo responsavel = Codigo.from(getUserId());
        AssumirOrdemServico cmd = AssumirOrdemServico.of(id, responsavel);

        service.handle(cmd);
    }

    @PutMapping(path = "{id}/transferir", consumes = APPLICATION_JSON_VALUE)
    public void transferir(@PathVariable OrdemServicoId id) {

        Codigo responsavel = Codigo.from(getUserId());
        TransferirOrdemServico cmd = TransferirOrdemServico.of(id, responsavel);

        service.transferir(cmd);
    }

    @PutMapping(path = "{id}/finalizar", consumes = APPLICATION_JSON_VALUE)
    public void finalizar(@PathVariable OrdemServicoId id) {

        Codigo responsavel = Codigo.from(getUserId());
        FinalizarOrdemServico cmd = FinalizarOrdemServico.of(id, responsavel);

        service.finalizar(cmd);
    }

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return name;
    }

}
