package br.edu.iff.ccc.bsi.SistemaHospitalar.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsulta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Medico medico;   

    @NotNull
    private LocalDateTime data;

    @Size(max = 500)
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comum paciente;


    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Comum getPaciente() {
        return paciente;
    }

    public void setPaciente(Comum paciente) {
        this.paciente = paciente;
    }
}
