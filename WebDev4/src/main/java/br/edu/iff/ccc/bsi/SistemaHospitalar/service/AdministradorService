package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Administrador;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repo;

    public Optional<Administrador> findById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Administrador não encontrado");
        }
    }

    public Administrador save(Administrador administrador) {
        try {
            return repo.save(administrador);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o administrador");
        }
    }

    public void deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o administrador");
        }
    }
}
