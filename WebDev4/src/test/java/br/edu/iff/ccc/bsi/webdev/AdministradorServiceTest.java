package br.edu.iff.ccc.bsi.webdev;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Administrador;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.AdministradorService;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.AdministradorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class AdministradorServiceTest {

	@Mock
	private AdministradorRepository administradorRepository;

	@InjectMocks
	private AdministradorService administradorService;

	AdministradorServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findById_quandoAdministradorExistente_deveRetornarAdministrador() {
		// Arrange
		Administrador administrador = new Administrador();
		administrador.setId(1);
		administrador.setNome("Admin Teste");

		given(administradorRepository.findById(1)).willReturn(Optional.of(administrador));

		// Act
		Optional<Administrador> resultado = administradorService.findById(1);

		// Assert
		assertThat(resultado).isPresent();
		assertThat(resultado.get()).isEqualTo(administrador);
	}

	@Test
	void save_quandoAdministradorSalvo_deveRetornarAdministrador() {
		// Arrange
		Administrador administrador = new Administrador();
		administrador.setNome("Novo Administrador");

		given(administradorRepository.save(administrador)).willReturn(administrador);

		// Act
		Administrador resultado = administradorService.save(administrador);

		// Assert
		assertThat(resultado).isEqualTo(administrador);
	}

	@Test
	void deleteById_quandoDeletadoComSucesso_naoDeveLancarExcecao() {
		// Arrange
		Administrador administrador = new Administrador();
		administrador.setId(1);

		// Act & Assert
		// exceção
		administradorService.deleteById(1);
	}
}
