package br.edu.iff.ccc.bsi.webdev;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.MedicoService;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;


    MedicoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_quandoMedicoExistente_deveRetornarMedico() {
        // Arrange
        Medico medico = new Medico();
        medico.setId(1);
        medico.setNome("Dr. Alan");

        given(medicoRepository.findById(1)).willReturn(Optional.of(medico));

        // Act
        Optional<Medico> resultado = medicoService.findById(1);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get()).isEqualTo(medico);
    }

    @Test
    void save_quandoMedicoSalvo_deveRetornarMedico() {
        // Arrange
        Medico medico = new Medico();
        medico.setNome("Dr. Novo Médico");

        given(medicoRepository.save(medico)).willReturn(medico);

        // Act
        Medico resultado = medicoService.save(medico);

        // Assert
        assertThat(resultado).isEqualTo(medico);
    }

    @Test
    void deleteById_quandoDeletadoComSucesso_naoDeveLancarExcecao() {
        // Arrange
        Medico medico = new Medico();
        medico.setId(1);

        // Act & Assert
        medicoService.deleteById(1);
    }
}
