package br.edu.iff.ccc.bsi.SistemaHospitalar.apirest.controller;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.MedicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @Operation(summary = "Buscar um médico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @GetMapping("/{id}")
    public Optional<Medico> buscarPorId(@PathVariable int id) {
        return service.findById(id);
    }

    @Operation(summary = "Salvar um novo médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico criado"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PostMapping
    public Medico salvar(@RequestBody Medico medico) {
        return service.save(medico);
    }

    @Operation(summary = "Deletar um médico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Médico deletado"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable int id) {
        service.deleteById(id);
    }
}

