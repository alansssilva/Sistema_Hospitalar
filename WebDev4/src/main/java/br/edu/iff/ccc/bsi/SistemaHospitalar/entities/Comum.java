package br.edu.iff.ccc.bsi.SistemaHospitalar.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comum extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private boolean possuiPlanoSaude;


    public boolean isPossuiPlanoSaude() {
        return possuiPlanoSaude;
    }

    public void setPossuiPlanoSaude(boolean possuiPlanoSaude) {
        this.possuiPlanoSaude = possuiPlanoSaude;
    }
}
