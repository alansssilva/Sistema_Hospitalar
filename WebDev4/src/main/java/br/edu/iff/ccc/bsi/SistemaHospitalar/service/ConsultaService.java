package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Consulta;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repo;

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

    public List<Consulta> findByMedicoNomeDescricao(String nomeMedico, String descricao) {
        try {
            return repo.findByMedicoNomeAndDescricao(nomeMedico, descricao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar consultas");
        }
    }
}
