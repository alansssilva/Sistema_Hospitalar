package br.edu.iff.ccc.bsi.SistemaHospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    // Método para buscar administrador pelo email e senha
    @Query("SELECT a FROM Administrador a WHERE a.email = :email AND a.senha = :senha")
    Administrador findByEmailAndSenha(String email, String senha);
}
