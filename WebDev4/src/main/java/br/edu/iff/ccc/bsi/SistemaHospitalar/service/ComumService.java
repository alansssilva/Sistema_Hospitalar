package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.ComumRepository;

@Service
public class ComumService {

    @Autowired
    private ComumRepository repo;

    public Optional<Comum> findById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Paciente comum não encontrado");
        }
    }

    public Comum save(Comum comum) {
        try {
            return repo.save(comum);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o paciente comum");
        }
    }

    public void deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o paciente comum");
        }
    }
}
