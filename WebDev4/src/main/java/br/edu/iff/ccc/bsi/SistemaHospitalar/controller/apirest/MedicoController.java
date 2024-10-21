package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.apirest;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.MedicoService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoService service;

	@Operation(summary = "Criar um novo médico")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Médico criado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class)) }),
			@ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
	@PostMapping
	public ResponseEntity<EntityModel<Medico>> createMedico(@RequestParam String nome, @RequestParam String email,
			@RequestParam String senha, @RequestParam String tipo, @RequestParam String cpf,
			@RequestParam String telefone, @RequestParam String dataNascimento, @RequestParam String endereco,
			@RequestParam String especialidade, @RequestParam String CRM) {

		Medico novoMedico = new Medico();
		novoMedico.setNome(nome);
		novoMedico.setEmail(email);
		novoMedico.setSenha(senha);
		novoMedico.setTipo(tipo);
		novoMedico.setCpf(cpf);
		novoMedico.setTelefone(telefone);
		novoMedico.setDataNascimento(dataNascimento);
		novoMedico.setEndereco(endereco);
		novoMedico.setEspecialidade(especialidade);
		novoMedico.setCRM(CRM);

		novoMedico = service.save(novoMedico);

		EntityModel<Medico> resource = EntityModel.of(novoMedico);
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).findById(novoMedico.getId())).withSelfRel();
		resource.add(selfLink);
		return ResponseEntity.status(201).body(resource);
	}

	@Operation(summary = "Buscar médico por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Médico encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class)) }),
			@ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Medico>> findById(@PathVariable int id) {
		Optional<Medico> medico = service.findById(id);
		if (medico.isPresent()) {
			EntityModel<Medico> resource = EntityModel.of(medico.get());
			Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).findById(id))
					.withSelfRel();
			resource.add(selfLink);
			Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class)
					.updateMedico(id, null, null, null, null, null, null, null, null, null, null)).withRel("update");
			resource.add(updateLink);
			Link deleteLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).deleteMedico(id)).withRel("delete");
			resource.add(deleteLink);
			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Atualizar um médico")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class)) }),
			@ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<Medico>> updateMedico(@PathVariable int id,
			@RequestParam(required = false) String nome, @RequestParam(required = false) String email,
			@RequestParam(required = false) String senha, @RequestParam(required = false) String tipo,
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String telefone,
			@RequestParam(required = false) String dataNascimento, @RequestParam(required = false) String endereco,
			@RequestParam(required = false) String especialidade, @RequestParam(required = false) String CRM) {

		Optional<Medico> optionalMedico = service.findById(id);
		if (optionalMedico.isPresent()) {
			Medico medico = optionalMedico.get();
			if (nome != null)
				medico.setNome(nome);
			if (email != null)
				medico.setEmail(email);
			if (senha != null)
				medico.setSenha(senha);
			if (tipo != null)
				medico.setTipo(tipo);
			if (cpf != null)
				medico.setCpf(cpf);
			if (telefone != null)
				medico.setTelefone(telefone);
			if (dataNascimento != null)
				medico.setDataNascimento(dataNascimento);
			if (endereco != null)
				medico.setEndereco(endereco);
			if (especialidade != null)
				medico.setEspecialidade(especialidade);
			if (CRM != null)
				medico.setCRM(CRM);

			Medico atualizado = service.save(medico);
			EntityModel<Medico> resource = EntityModel.of(atualizado);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).findById(atualizado.getId()))
					.withSelfRel();
			resource.add(selfLink);
			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Deletar um médico")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Médico deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMedico(@PathVariable int id) {
		if (!service.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Buscar médico por email e senha")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Médico encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class)) }),
			@ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content) })
	@PostMapping("/login")
	public ResponseEntity<EntityModel<Medico>> findByEmailAndSenha(@RequestParam String email,
			@RequestParam String senha) {
		Optional<Medico> medico = Optional.ofNullable(service.findByEmailAndSenha(email, senha));
		if (medico.isPresent()) {
			EntityModel<Medico> resource = EntityModel.of(medico.get());
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).findById(medico.get().getId()))
					.withSelfRel();
			resource.add(selfLink);
			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Buscar todos os médicos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de médicos encontrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class)) }),
			@ApiResponse(responseCode = "404", description = "Nenhum médico encontrado", content = @Content) })
	@GetMapping
	public ResponseEntity<List<EntityModel<Medico>>> findAll() {
		List<Medico> medicos = service.findAll();
		if (medicos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		List<EntityModel<Medico>> resources = medicos.stream().map(medico -> {
			EntityModel<Medico> resource = EntityModel.of(medico);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).findById(medico.getId())).withSelfRel();
			resource.add(selfLink);
			return resource;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(resources);
	}
}
