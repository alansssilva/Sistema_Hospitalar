package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Administrador;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repo;

    public Optional<Administrador> findById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Administrador não encontrado: " + e.getMessage());
        }
    }

    public Administrador save(Administrador administrador) {
        try {
            return repo.save(administrador);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o administrador: " + e.getMessage());
        }
    }

    public void deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o administrador: " + e.getMessage());
        }
    }

    public Administrador findByEmailAndSenha(String email, String senha) {
        try {
            return repo.findByEmailAndSenha(email, senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar administrador pelo email e senha: " + e.getMessage());
        }
    }
}
