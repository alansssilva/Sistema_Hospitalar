package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repo;

    public Optional<Medico> findById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Médico não encontrado");
        }
    }

    public Medico save(Medico medico) {
        try {
            return repo.save(medico);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o médico");
        }
    }

    public void deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o médico");
        }
    }
}
