package com.tiagoamp.usuarioapi.controller;

import com.tiagoamp.usuarioapi.dto.RootEntryPointResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootEntryPointController {

    @GetMapping
    public ResponseEntity<RootEntryPointResponse> getRoot() {
        RootEntryPointResponse resp = new RootEntryPointResponse()
                .add(linkTo(methodOn(UsuarioController.class).getUsuarios())
                        .withRel("usuarios"));
        return ResponseEntity.ok(resp);
    }
}
