package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Usuario;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Optional<Usuario> findById(int id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public Usuario save(Usuario usuario) {
        try {
            return repo.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o usuário");
        }
    }

    public void deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o usuário");
        }
    }
}
