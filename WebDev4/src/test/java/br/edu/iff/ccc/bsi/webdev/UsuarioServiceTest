package br.edu.iff.ccc.bsi.webdev;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Usuario;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.UsuarioService;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_quandoUsuarioExistente_deveRetornarUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Teste");

        given(usuarioRepository.findById(1)).willReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> resultado = usuarioService.findById(1);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get()).isEqualTo(usuario);
    }

    @Test
    void save_quandoUsuarioSalvo_deveRetornarUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNome("Novo Usuário");

        given(usuarioRepository.save(usuario)).willReturn(usuario);

        // Act
        Usuario resultado = usuarioService.save(usuario);

        // Assert
        assertThat(resultado).isEqualTo(usuario);
    }

    @Test
    void deleteById_quandoDeletadoComSucesso_naoDeveLancarExcecao() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);

        // Act & Assert
        usuarioService.deleteById(1);
    }
}
