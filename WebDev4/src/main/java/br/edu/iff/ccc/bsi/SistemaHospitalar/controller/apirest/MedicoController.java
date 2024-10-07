package com.tiagoamp.usuarioapi.controller;

import com.tiagoamp.usuarioapi.dto.UsuarioResponse;
import com.tiagoamp.usuarioapi.dto.RootEntryPointResponse;
import com.tiagoamp.usuarioapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Buscar todos os usuários", description = "Retorna uma lista de usuários cadastrados.")
    @GetMapping
    public ResponseEntity<RootEntryPointResponse> getUsuarios() {
        RootEntryPointResponse resp = new RootEntryPointResponse()
                .add(linkTo(methodOn(UsuarioController.class).getUsuarios())
                        .withRel("usuarios"));
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable("id") Long id) {
        UsuarioResponse usuarioResponse = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioResponse);
    }
}
