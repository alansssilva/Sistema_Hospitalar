package br.edu.iff.ccc.bsi.SistemaHospitalar.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime dataAdmissao;

	public LocalDateTime getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDateTime dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
}
