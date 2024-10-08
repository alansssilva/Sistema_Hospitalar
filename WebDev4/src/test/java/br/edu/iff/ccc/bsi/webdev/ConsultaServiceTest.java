package br.edu.iff.ccc.bsi.webdev;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Consulta;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.ConsultaRepository;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ConsultaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ConsultaService consultaService;

    ConsultaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_quandoConsultaExistente_deveRetornarConsulta() {
        // Arrange
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(1);
        consulta.setDescricao("Consulta de rotina");

        given(consultaRepository.findById(1)).willReturn(Optional.of(consulta));

        // Act
        Optional<Consulta> resultado = consultaService.findById(1);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get()).isEqualTo(consulta);
    }

    @Test
    void save_quandoConsultaSalva_deveRetornarConsulta() {
        // Arrange
        Consulta consulta = new Consulta();
        consulta.setDescricao("Nova consulta");

        given(consultaRepository.save(consulta)).willReturn(consulta);

        // Act
        Consulta resultado = consultaService.save(consulta);

        // Assert
        assertThat(resultado).isEqualTo(consulta);
    }

    @Test
    void deleteById_quandoDeletadoComSucesso_naoDeveLancarExcecao() {
        // Arrange
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(1);

        // Act & Assert
        consultaService.deleteById(1);
    }

    @Test
    void findByMedicoNomeDescricao_quandoConsultasExistentes_deveRetornarListaDeConsultas() {
        // Arrange
        Consulta consulta1 = new Consulta();
        consulta1.setDescricao("Consulta de rotina");

        Consulta consulta2 = new Consulta();
        consulta2.setDescricao("Consulta de emergência");

        List<Consulta> consultas = Arrays.asList(consulta1, consulta2);

        given(consultaRepository.findByMedicoNomeAndDescricao("Dr. João", "Consulta")).willReturn(consultas);

        // Act
        List<Consulta> resultado = consultaService.findByMedicoNomeDescricao("Dr. João", "Consulta");

        // Assert
        assertThat(resultado).hasSize(2);
        assertThat(resultado).contains(consulta1, consulta2);
    }
}

