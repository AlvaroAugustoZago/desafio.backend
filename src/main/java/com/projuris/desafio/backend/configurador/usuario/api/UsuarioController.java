package com.projuris.desafio.backend.configurador.usuario.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projuris.desafio.backend.configurador.usuario.app.CriarUsuario;
import com.projuris.desafio.backend.configurador.usuario.app.UsuarioService;
import com.projuris.desafio.backend.configurador.usuario.dominio.UsuarioId;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping(path = UsuarioController.PATH, produces = APPLICATION_JSON_VALUE)
public class UsuarioController {

    public static final String PATH = "/api/v1/usuarios/"; //NOSONAR

    private final UsuarioService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody CriarUsuario cmd) {

        UsuarioId id = service.handle(cmd);

        return ResponseEntity.created(fromCurrentRequest()
            .path("/").path(id.toUUID()).build().toUri())
            .build();
    }
}
