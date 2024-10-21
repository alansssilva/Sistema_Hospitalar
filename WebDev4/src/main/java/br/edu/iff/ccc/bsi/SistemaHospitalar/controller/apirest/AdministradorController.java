package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.apirest;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Administrador;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

	@Autowired
	private AdministradorService service;

	@Operation(summary = "Criar um novo administrador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Administrador criado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class)) }),
			@ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
	@PostMapping
	public ResponseEntity<EntityModel<Administrador>> createAdministrador(@RequestParam String nome,
			@RequestParam String email, @RequestParam String senha, @RequestParam String tipo, @RequestParam String cpf,
			@RequestParam String telefone, @RequestParam String dataNascimento, @RequestParam String endereco,
			@RequestParam String dataAdmissao) {

		Administrador novoAdministrador = new Administrador();
		novoAdministrador.setNome(nome);
		novoAdministrador.setEmail(email);
		novoAdministrador.setSenha(senha);
		novoAdministrador.setTipo(tipo);
		novoAdministrador.setCpf(cpf);
		novoAdministrador.setTelefone(telefone);
		novoAdministrador.setDataNascimento(dataNascimento);
		novoAdministrador.setEndereco(endereco);
		novoAdministrador.setDataAdmissao(dataAdmissao);

		novoAdministrador = service.save(novoAdministrador);

		EntityModel<Administrador> resource = EntityModel.of(novoAdministrador);
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(AdministradorController.class).findById(novoAdministrador.getId()))
				.withSelfRel();
		resource.add(selfLink);

		return ResponseEntity.status(201).body(resource);
	}

	@Operation(summary = "Buscar administrador por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Administrador encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class)) }),
			@ApiResponse(responseCode = "404", description = "Administrador não encontrado", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Administrador>> findById(@PathVariable int id) {
		Optional<Administrador> administrador = service.findById(id);

		if (administrador.isPresent()) {
			EntityModel<Administrador> resource = EntityModel.of(administrador.get());
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(AdministradorController.class).findById(id)).withSelfRel();
			resource.add(selfLink);

			Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdministradorController.class)
					.updateAdministrador(id, null, null, null, null, null, null, null, null, null)).withRel("update");
			resource.add(updateLink);

			Link deleteLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(AdministradorController.class).deleteAdministrador(id))
					.withRel("delete");
			resource.add(deleteLink);

			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Atualizar um administrador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Administrador atualizado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class)) }),
			@ApiResponse(responseCode = "404", description = "Administrador não encontrado", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<Administrador>> updateAdministrador(@PathVariable int id,
			@RequestParam(required = false) String nome, @RequestParam(required = false) String email,
			@RequestParam(required = false) String senha, @RequestParam(required = false) String tipo,
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String telefone,
			@RequestParam(required = false) String dataNascimento, @RequestParam(required = false) String endereco,
			@RequestParam(required = false) String dataAdmissao) {

		Optional<Administrador> optionalAdministrador = service.findById(id);

		if (optionalAdministrador.isPresent()) {
			Administrador administrador = optionalAdministrador.get();
			if (nome != null)
				administrador.setNome(nome);
			if (email != null)
				administrador.setEmail(email);
			if (senha != null)
				administrador.setSenha(senha);
			if (tipo != null)
				administrador.setTipo(tipo);
			if (cpf != null)
				administrador.setCpf(cpf);
			if (telefone != null)
				administrador.setTelefone(telefone);
			if (dataNascimento != null)
				administrador.setDataNascimento(dataNascimento);
			if (endereco != null)
				administrador.setEndereco(endereco);
			if (dataAdmissao != null)
				administrador.setDataAdmissao(dataAdmissao);

			Administrador atualizado = service.save(administrador);
			EntityModel<Administrador> resource = EntityModel.of(atualizado);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(AdministradorController.class).findById(atualizado.getId()))
					.withSelfRel();
			resource.add(selfLink);

			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Deletar um administrador")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Administrador deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Administrador não encontrado", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdministrador(@PathVariable int id) {
		if (service.findById(id).isPresent()) {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Autenticar administrador por email e senha")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Administrador autenticado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class)) }),
			@ApiResponse(responseCode = "404", description = "Administrador não encontrado", content = @Content) })
	@PostMapping("/login")
	public ResponseEntity<EntityModel<Administrador>> login(@RequestParam String email, @RequestParam String senha) {
		Optional<Administrador> administrador = Optional.ofNullable(service.findByEmailAndSenha(email, senha));
		if (administrador.isPresent()) {
			EntityModel<Administrador> resource = EntityModel.of(administrador.get());
			Link selfLink = WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(AdministradorController.class).findById(administrador.get().getId()))
					.withSelfRel();
			resource.add(selfLink);
			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
