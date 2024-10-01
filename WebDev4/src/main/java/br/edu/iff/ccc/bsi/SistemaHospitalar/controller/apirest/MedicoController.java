package com.tiagoamp.usuarioapi.controller;

import com.tiagoamp.usuarioapi.dto.*;
import com.tiagoamp.usuarioapi.service.UsuarioService;
import com.tiagoamp.usuarioapi.util.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper usuarioMapper;

    @Operation(summary = "Buscar usuários", description = "Obter usuários registrados")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getUsuarios(
            @RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "sort", required = false, defaultValue = "nome") String sortField,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String sortDirection) {
        var usuarios = service.findUsuarios(size, pageNumber, sortField, sortDirection);
        var usuariosResp = usuarios.stream()
                .map(usuarioMapper::toResponse)
                .map(u -> u.add(linkTo(methodOn(this.getClass()).getUsuario(u.getId())).withSelfRel()))
                .toList();
        return ResponseEntity.ok(usuariosResp);
    }

    @Operation(summary = "Buscar usuário por id", description = "Buscar usuário por id",
            responses = {@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable("id") Integer id) {
        var usuario = service.findUsuarioById(id);
        var usuarioResp = usuarioMapper.toResponse(usuario)
                .add(linkTo(methodOn(this.getClass()).getUsuarios(null, null, null, null)).withRel("usuarios"));
        return ResponseEntity.ok(usuarioResp);
    }

    @Operation(summary = "Registrar novo usuário", description = "Registrar novo usuário",
            responses = {@ApiResponse(responseCode = "400", description = "Dados de requisição inválidos",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody @Valid UsuarioRequest request) {
        var usuario = usuarioMapper.toModel(request);
        usuario = service.createUsuario(usuario);
        var usuarioResp = usuarioMapper.toResponse(usuario);
        return ResponseEntity.created(URI.create("/usuarios/" + usuario.getId())).body(usuarioResp);
    }

    @Operation(summary = "Atualizar informações do usuário", description = "Atualizar informações do usuário",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
            })
    @PutMapping("{id}")
    public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable("id") Integer id, @RequestBody @Valid UsuarioRequest request) {
        var usuario = usuarioMapper.toModel(request);
        usuario.setId(id);
        usuario = service.updateUsuario(usuario);
        var usuarioResp = usuarioMapper.toResponse(usuario);
        return ResponseEntity.ok(usuarioResp);
    }

    @Operation(
            summary = "Deletar usuário por id", description = "Deletar usuário por id",
            responses = {@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Integer id) {
        service.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
