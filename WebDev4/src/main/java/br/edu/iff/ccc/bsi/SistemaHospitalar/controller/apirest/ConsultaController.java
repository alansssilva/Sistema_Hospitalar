package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.apirest;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Consulta;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ConsultaService;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ComumService; 
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService service;

	@Autowired
	private ComumService comumService; 

	@Autowired
	private MedicoService medicoService; 


	@Operation(summary = "Buscar todos os pacientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pacientes encontrados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Comum.class)) }),
			@ApiResponse(responseCode = "404", description = "Nenhum paciente encontrado", content = @Content) })
	@GetMapping("/pacientes")
	public ResponseEntity<List<EntityModel<Comum>>> findAllPacientes() {
		List<Comum> pacientes = comumService.findAll();
		if (pacientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		List<EntityModel<Comum>> resources = pacientes.stream().map(paciente -> {
			EntityModel<Comum> resource = EntityModel.of(paciente);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).findById(paciente.getId())) 
					.withSelfRel();
			resource.add(selfLink);
			return resource;
		}).toList();

		return ResponseEntity.ok(resources);
	}

	@Operation(summary = "Buscar todos os médicos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Médicos encontrados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Medico.class)) }),
			@ApiResponse(responseCode = "404", description = "Nenhum médico encontrado", content = @Content) })
	@GetMapping("/medicos")
	public ResponseEntity<List<EntityModel<Medico>>> findAllMedicos() {
		List<Medico> medicos = medicoService.findAll();
		if (medicos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		List<EntityModel<Medico>> resources = medicos.stream().map(medico -> {
			EntityModel<Medico> resource = EntityModel.of(medico);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).findById(medico.getId())) 																												
					.withSelfRel();
			resource.add(selfLink);
			return resource;
		}).toList();

		return ResponseEntity.ok(resources);
	}

	@Operation(summary = "Criar uma nova consulta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Consulta criada com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)) }),
			@ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
	@PostMapping
	public ResponseEntity<EntityModel<Consulta>> createConsulta(@RequestParam Medico medicoId,
			@RequestParam Comum pacienteId, @RequestParam LocalDateTime dataHora, @RequestParam String descricao) {

		Consulta novaConsulta = new Consulta();
		novaConsulta.setMedico(medicoId);
		novaConsulta.setPaciente(pacienteId);
		novaConsulta.setData(dataHora);
		novaConsulta.setDescricao(descricao);

		novaConsulta = service.save(novaConsulta);

		EntityModel<Consulta> resource = EntityModel.of(novaConsulta);
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).findById(novaConsulta.getIdConsulta()))
				.withSelfRel();
		resource.add(selfLink);
		return ResponseEntity.status(201).body(resource);
	}

	@Operation(summary = "Buscar consulta por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Consulta encontrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)) }),
			@ApiResponse(responseCode = "404", description = "Consulta não encontrada", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Consulta>> findById(@PathVariable int id) {
		Optional<Consulta> consulta = service.findById(id);
		if (consulta.isPresent()) {
			EntityModel<Consulta> resource = EntityModel.of(consulta.get());
			Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).findById(id))
					.withSelfRel();
			resource.add(selfLink);

			Link updateLink = WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(ConsultaController.class).updateConsulta(id, null, null, null, null))
					.withRel("update");
			resource.add(updateLink);

			Link deleteLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).deleteConsulta(id)).withRel("delete");
			resource.add(deleteLink);

			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Atualizar uma consulta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)) }),
			@ApiResponse(responseCode = "404", description = "Consulta não encontrada", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<Consulta>> updateConsulta(@PathVariable int id,
			@RequestParam(required = false) Medico medicoId, @RequestParam(required = false) Comum pacienteId,
			@RequestParam(required = false) LocalDateTime dataHora, @RequestParam(required = false) String descricao) {

		Optional<Consulta> optionalConsulta = service.findById(id);
		if (optionalConsulta.isPresent()) {
			Consulta consulta = optionalConsulta.get();
			if (medicoId != null)
				consulta.setMedico(medicoId);
			if (pacienteId != null)
				consulta.setPaciente(pacienteId);
			if (dataHora != null)
				consulta.setData(dataHora);
			if (descricao != null)
				consulta.setDescricao(descricao);

			Consulta atualizado = service.save(consulta);
			EntityModel<Consulta> resource = EntityModel.of(atualizado);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).findById(atualizado.getIdConsulta()))
					.withSelfRel();
			resource.add(selfLink);
			return ResponseEntity.ok(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Deletar uma consulta")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Consulta deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Consulta não encontrada", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConsulta(@PathVariable int id) {
		if (!service.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Buscar consultas por nome do médico e descrição")
	@GetMapping("/buscar")
	public ResponseEntity<List<EntityModel<Consulta>>> findByMedicoNomeDescricao(@RequestParam String nomeMedico,
			@RequestParam String descricao) {
		List<Consulta> consultas = service.findByMedicoNomeDescricao(nomeMedico, descricao);
		List<EntityModel<Consulta>> resources = consultas.stream().map(consulta -> {
			EntityModel<Consulta> resource = EntityModel.of(consulta);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).findById(consulta.getIdConsulta()))
					.withSelfRel();
			resource.add(selfLink);
			return resource;
		}).toList();

		return ResponseEntity.ok(resources);
	}

	@Operation(summary = "Buscar todas as consultas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Consultas encontradas", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)) }),
			@ApiResponse(responseCode = "404", description = "Nenhuma consulta encontrada", content = @Content) })
	@GetMapping
	public ResponseEntity<List<EntityModel<Consulta>>> findAll() {
		List<Consulta> consultas = service.findAll();
		if (consultas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		List<EntityModel<Consulta>> resources = consultas.stream().map(consulta -> {
			EntityModel<Consulta> resource = EntityModel.of(consulta);
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).findById(consulta.getIdConsulta()))
					.withSelfRel();
			resource.add(selfLink);
			return resource;
		}).toList();

		return ResponseEntity.ok(resources);
	}

}
