package br.edu.iff.ccc.bsi.SistemaHospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;

public interface ComumRepository extends JpaRepository<Comum, Integer> {
}
