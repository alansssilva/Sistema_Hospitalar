package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.List;
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
            throw new RuntimeException("Paciente comum não encontrado: " + e.getMessage());
        }
    }

    public Comum save(Comum comum) {
        try {
            return repo.save(comum);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o paciente comum: " + e.getMessage());
        }
    }

    public void deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o paciente comum: " + e.getMessage());
        }
    }

    public List<Comum> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pacientes comuns: " + e.getMessage());
        }
    }
    
    public Comum findByEmailAndSenha(String email, String senha) {
        try {
            return repo.findByEmailAndSenha(email, senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar paciente comum pelo email e senha: " + e.getMessage());
        }
    }

	public void removerPaciente(Long id) {
		// TODO Auto-generated method stub
		
	}
}
