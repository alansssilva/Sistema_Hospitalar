package br.edu.iff.ccc.bsi.SistemaHospitalar.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class Medico extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String especialidade;
	private String CRM;

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getCRM() {
		return CRM;
	}

	public void setCRM(String CRM) {
		this.CRM = CRM;
	}
}
