package br.edu.iff.ccc.bsi.SistemaHospitalar.service;

import java.util.List;
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
			throw new RuntimeException("Médico não encontrado: " + e.getMessage());
		}
	}

	public Medico save(Medico medico) {
		try {
			return repo.save(medico);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar o médico: " + e.getMessage());
		}
	}

	public void deleteById(int id) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar o médico: " + e.getMessage());
		}
	}

	public Medico findByEmailAndSenha(String email, String senha) {
		try {
			return repo.findByEmailAndSenha(email, senha);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar médico pelo email e senha: " + e.getMessage());
		}

	}

	public List<Medico> findAll() {
		try {
			return repo.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar médicos: " + e.getMessage());
		}
	}

	public void removerMedico(Long id) {
		// TODO Auto-generated method stub
		
	}
}
