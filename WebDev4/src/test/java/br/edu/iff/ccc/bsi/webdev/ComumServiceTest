package br.edu.iff.ccc.bsi.webdev;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ComumService;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.ComumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

class ComumServiceTest {

    @Mock
    private ComumRepository comumRepository;

    @InjectMocks
    private ComumService comumService;

    ComumServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_quandoComumExistente_deveRetornarComum() {
        // Arrange
        Comum comum = new Comum();
        comum.setId(1);
        comum.setNome("Paciente Comum Teste");

        given(comumRepository.findById(1)).willReturn(Optional.of(comum));

        // Act
        Optional<Comum> resultado = comumService.findById(1);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get()).isEqualTo(comum);
    }

    @Test
    void save_quandoComumSalvo_deveRetornarComum() {
        // Arrange
        Comum comum = new Comum();
        comum.setNome("Novo Paciente Comum");

        given(comumRepository.save(comum)).willReturn(comum);

        // Act
        Comum resultado = comumService.save(comum);

        // Assert
        assertThat(resultado).isEqualTo(comum);
    }

    @Test
    void deleteById_quandoDeletadoComSucesso_naoDeveLancarExcecao() {
        // Arrange
        int id = 1;

        willDoNothing().given(comumRepository).deleteById(id);

        // Act & Assert
        comumService.deleteById(id);
    }
}

