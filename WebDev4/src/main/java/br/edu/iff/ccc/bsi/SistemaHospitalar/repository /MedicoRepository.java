package br.edu.iff.ccc.bsi.SistemaHospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    // Método para buscar médico pelo email e senha
    @Query("SELECT m FROM Medico m WHERE m.email = :email AND m.senha = :senha")
    Medico findByEmailAndSenha(String email, String senha);
}
