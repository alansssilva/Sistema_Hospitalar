package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.apirest;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ComumService;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Paciente")
public class ComumController {

	@Autowired
	private ComumService service;

	@Operation(summary = "Criar um novo Paciente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Comum criado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comum.class)) }),
			@ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
	@PostMapping
	public ResponseEntity<EntityModel<Comum>> createComum(@RequestParam String nome, @RequestParam String email,
			@RequestParam String senha, @RequestParam String tipo, @RequestParam String cpf,
			@RequestParam String telefone, @RequestParam String dataNascimento, @RequestParam String endereco,
			@RequestParam boolean possuiPlanoSaude) {

		Comum novoComum = new Comum();
		novoComum.setNome(nome);
		novoComum.setEmail(email);
		novoComum.setSenha(senha);
		novoComum.setTipo(tipo);
		novoComum.setCpf(cpf);
		novoComum.setTelefone(telefone);
		novoComum.setDataNascimento(dataNascimento);
		novoComum.setEndereco(endereco);
		novoComum.setPossuiPlanoSaude(possuiPlanoSaude);

		Comum comumCriado = service.save(novoComum);

		EntityModel<Comum> entityModel = EntityModel.of(comumCriado, WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ComumController.class).findById(comumCriado.getId())).withSelfRel());

		return ResponseEntity.status(201).body(entityModel);
	}

	@Operation(summary = "Buscar Paciente por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário comum encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comum.class)) }),
			@ApiResponse(responseCode = "404", description = "Usuário comum não encontrado", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Comum>> findById(@PathVariable int id) {
		Optional<Comum> comum = service.findById(id);
		if (comum.isPresent()) {
			EntityModel<Comum> resource = EntityModel.of(comum.get());
			Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComumController.class).findById(id))
					.withSelfRel();
			resource.add(selfLink);

			Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComumController.class).updateComum(id,
					null, null, null, null, null, null, null, null, null)).withRel("update");
			resource.add(updateLink);

			Link deleteLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ComumController.class).deleteComum(id)).withRel("delete");
			resource.add(deleteLink);

			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Atualizar um Paciente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comum atualizado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comum.class)) }),
			@ApiResponse(responseCode = "404", description = "Comum não encontrado", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<Comum>> updateComum(@PathVariable int id,
			@RequestParam(required = false) String nome, @RequestParam(required = false) String email,
			@RequestParam(required = false) String senha, @RequestParam(required = false) String tipo,
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String telefone,
			@RequestParam(required = false) String dataNascimento, @RequestParam(required = false) String endereco,
			@RequestParam(required = false) Boolean possuiPlanoSaude) {

		Optional<Comum> optionalComum = service.findById(id);

		if (optionalComum.isPresent()) {
			Comum comum = optionalComum.get();

			if (nome != null)
				comum.setNome(nome);
			if (email != null)
				comum.setEmail(email);
			if (senha != null)
				comum.setSenha(senha);
			if (tipo != null)
				comum.setTipo(tipo);
			if (cpf != null)
				comum.setCpf(cpf);
			if (telefone != null)
				comum.setTelefone(telefone);
			if (dataNascimento != null)
				comum.setDataNascimento(dataNascimento);
			if (endereco != null)
				comum.setEndereco(endereco);
			if (possuiPlanoSaude != null)
				comum.setPossuiPlanoSaude(possuiPlanoSaude);

			Comum comumAtualizado = service.save(comum);

			EntityModel<Comum> entityModel = EntityModel.of(comumAtualizado, WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ComumController.class).findById(id)).withSelfRel());

			return ResponseEntity.ok(entityModel);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Deletar um Paciente")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Usuário comum deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário comum não encontrado", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComum(@PathVariable int id) {
		if (!service.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Buscar todos os Pacientes no sistema")
	@GetMapping
	public ResponseEntity<List<EntityModel<Comum>>> findAll() {
		List<Comum> comuns = service.findAll();
		List<EntityModel<Comum>> resources = comuns.stream().map(comum -> {
			EntityModel<Comum> resource = EntityModel.of(comum);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ComumController.class).findById(comum.getId())).withSelfRel();
			resource.add(selfLink);
			return resource;
		}).toList();

		return ResponseEntity.ok(resources);
	}

	@Operation(summary = "Buscar paciente por email e senha")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Paciente encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comum.class)) }),
			@ApiResponse(responseCode = "404", description = "Paciente não encontrado", content = @Content) })
	@PostMapping("/login")
	public ResponseEntity<EntityModel<Comum>> findByEmailAndSenha(@RequestParam String email,
			@RequestParam String senha) {
		Optional<Comum> comum = Optional.ofNullable(service.findByEmailAndSenha(email, senha));
		if (comum.isPresent()) {
			EntityModel<Comum> resource = EntityModel.of(comum.get());
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ComumController.class).findById(comum.get().getId()))
					.withSelfRel();
			resource.add(selfLink);
			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
