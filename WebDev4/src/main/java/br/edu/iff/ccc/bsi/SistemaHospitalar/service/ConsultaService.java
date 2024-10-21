package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Consulta;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repo;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ComumService comumService;

    public Optional<Consulta> findById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Consulta não encontrada");
        }
    }

    public Consulta save(Consulta consulta) {
        try {
            return repo.save(consulta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a consulta");
        }
    }

    public void deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a consulta");
        }
    }

    public List<Medico> findAllMedicos() {
        try {
            return medicoService.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar médicos disponíveis: " + e.getMessage());
        }
    }

    public List<Comum> findAllPacientes() {
        try {
            return comumService.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pacientes disponíveis: " + e.getMessage());
        }
    }

    public List<Consulta> findByMedicoNomeDescricao(String nomeMedico, String descricao) {
        try {
            return repo.findByMedicoNomeAndDescricao(nomeMedico, descricao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar consultas");
        }
    }

    public List<Consulta> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as consultas: " + e.getMessage());
        }
    }
}
