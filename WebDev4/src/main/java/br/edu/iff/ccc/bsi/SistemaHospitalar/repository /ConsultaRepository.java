package br.edu.iff.ccc.bsi.SistemaHospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Consulta;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    //QueryMethod buscando consultas por nome do médico e descrição:
    @Query("SELECT c FROM Consulta c WHERE c.medico.nome = :nomeMedico AND c.descricao LIKE %:descricao%")
    List<Consulta> findByMedicoNomeAndDescricao(String nomeMedico, String descricao);
}
