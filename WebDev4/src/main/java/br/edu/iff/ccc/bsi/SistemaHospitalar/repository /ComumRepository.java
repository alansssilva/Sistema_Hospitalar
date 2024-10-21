package br.edu.iff.ccc.bsi.SistemaHospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;

public interface ComumRepository extends JpaRepository<Comum, Integer> {

    // Método para buscar paciente comum pelo email e senha
    @Query("SELECT c FROM Comum c WHERE c.email = :email AND c.senha = :senha")
    Comum findByEmailAndSenha(String email, String senha);
}
