package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.view;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ComumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComumViewController {

	@Autowired
	private ComumService pacienteService;

	@GetMapping("/cadastroPaciente")
	public String showCadastroPacienteForm(Model model) {
		model.addAttribute("title", "Cadastro de Paciente");
		model.addAttribute("content", "cadastroPaciente :: content");
		return "CadastroPaciente";
	}

	@PostMapping("/criarPaciente")
	public String createPaciente(@RequestParam String nome, @RequestParam String email, @RequestParam String senha,
			@RequestParam String cpf, @RequestParam String telefone, @RequestParam String dataNascimento,
			@RequestParam String endereco, @RequestParam boolean possuiPlanoSaude, Model model) {
		try {
			Comum novoPaciente = new Comum();
			novoPaciente.setNome(nome);
			novoPaciente.setEmail(email);
			novoPaciente.setSenha(senha);
			novoPaciente.setCpf(cpf);
			novoPaciente.setTelefone(telefone);
			novoPaciente.setDataNascimento(dataNascimento);
			novoPaciente.setEndereco(endereco);
			novoPaciente.setPossuiPlanoSaude(possuiPlanoSaude);

			pacienteService.save(novoPaciente);

			model.addAttribute("successMessage", "Paciente cadastrado com sucesso!");
			return "redirect:/main";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Erro ao cadastrar paciente: " + e.getMessage());
			return "redirect:/CadastroPaciente";
		}
	}

	@GetMapping("/loginPaciente")
	public String showLoginForm(Model model) {
		model.addAttribute("title", "Login de Paciente");
		model.addAttribute("content", "LoginPaciente :: content");
		return "LoginPaciente";
	}

	@PostMapping("/loginPaciente")
	public String login(@RequestParam String email, @RequestParam String senha, Model model) {
		Comum paciente = pacienteService.findByEmailAndSenha(email, senha);

		if (paciente != null) {
			// Login bem-sucedido
			model.addAttribute("paciente", paciente);
			return "Layout2"; 
		} else {
			// Login falhou
			model.addAttribute("erro", "Email ou senha inválidos");
			return "LoginPaciente"; 
		}
	}
}
