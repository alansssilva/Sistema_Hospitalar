package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.view;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class ViewMainController {

	@GetMapping
	public String mainPage(Model model) {
		model.addAttribute("title", "Sistema Hospitalar");
		return "layout";
	}

	@GetMapping("/loginMedico")
	public String loginMedico() {
		return "LoginMedico";
	}

	@GetMapping("/loginAdministrador")
	public String loginAdministrador() {
		return "LoginAdministrador";
	}

	@GetMapping("/loginPaciente")
	public String loginPaciente() {
		return "LoginPaciente";
	}

	@GetMapping("/cadastroMedico")
	public String cadastroMedico() {
		return "CadastroMedico";
	}

	@GetMapping("/cadastroPaciente")
	public String cadastroPaciente() {
		return "CadastroPaciente";
	}

	@GetMapping("/cadastroAdministrador")
	public String cadastroAdministrador() {
		return "CadastroAdministrador";
	}

	// ----------------------------------------------------------------------------------------------------------

	@GetMapping("/layout2")
	public String perfilPaciente(Model model) {
		model.addAttribute("title", "Perfil do Paciente");
		return "Layout2";
	}

	@GetMapping("/agendarConsulta")
	public String agendarConsulta(Model model) {
		return "MarcarConsulta";
	}
}
