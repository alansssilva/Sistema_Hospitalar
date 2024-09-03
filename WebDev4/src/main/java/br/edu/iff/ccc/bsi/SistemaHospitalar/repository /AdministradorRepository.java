package br.edu.iff.ccc.bsi.SistemaHospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
