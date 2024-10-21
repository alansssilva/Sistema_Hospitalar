package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.view;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicoViewController {

	@Autowired
	private MedicoService medicoService;

	@GetMapping("/cadastroMedico")
	public String showCadastroMedicoForm(Model model) {
		model.addAttribute("title", "Cadastro de Médico");
		model.addAttribute("content", "cadastroMedico :: content");
		return "CadastroMedico";
	}

	@PostMapping("/criarMedico")
	public String createMedico(@RequestParam String nome, @RequestParam String email, @RequestParam String senha,
			@RequestParam String cpf, @RequestParam String telefone, @RequestParam String dataNascimento,
			@RequestParam String endereco, @RequestParam String especialidade, @RequestParam String crm, Model model) {
		try {
			Medico novoMedico = new Medico();
			novoMedico.setNome(nome);
			novoMedico.setEmail(email);
			novoMedico.setSenha(senha);
			novoMedico.setCpf(cpf);
			novoMedico.setTelefone(telefone);
			novoMedico.setDataNascimento(dataNascimento);
			novoMedico.setEndereco(endereco);
			novoMedico.setEspecialidade(especialidade);
			novoMedico.setCRM(crm);

			medicoService.save(novoMedico);

			model.addAttribute("mensagem", "Médico cadastrado com sucesso!");
		} catch (Exception e) {
			model.addAttribute("erro", "Erro ao cadastrar médico: " + e.getMessage());
		}

		model.addAttribute("title", "Cadastro de Médico");
		model.addAttribute("content", "cadastroMedico :: content");
		return "redirect:/main";
	}
//-----------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/loginMedico")
	public String showLoginForm(Model model) {
		model.addAttribute("title", "Login de Médico");
		model.addAttribute("content", "LoginMedico :: content");
		return "LoginMedico";
	}

	@PostMapping("/loginMedico")
	public String login(@RequestParam String email, @RequestParam String senha, Model model) {
		Medico medico = medicoService.findByEmailAndSenha(email, senha);

		if (medico != null) {
			// Login bem-sucedido.
			model.addAttribute("medico", medico);
			return "Layout3"; 
		} else {
			// Login falhou
			model.addAttribute("erro", "Email ou senha inválidos");
			return "LoginMedico"; 
		}
	}
}
