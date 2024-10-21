package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.view;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Administrador;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdministradorViewController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/cadastroAdministrador")
    public String showCadastroAdministradorForm(Model model) {
        model.addAttribute("title", "Cadastro de Administrador");
        model.addAttribute("content", "cadastroAdministrador :: content");
        return "CadastroAdministrador";
    }

    @PostMapping("/criarAdministrador")
    public String createAdministrador(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String cpf,
            @RequestParam String senha,
            @RequestParam String telefone, 
            @RequestParam String dataNascimento, 
            @RequestParam String endereco, 
            @RequestParam String dataAdmissao, 
            RedirectAttributes redirectAttributes) {
        try {
            Administrador novoAdministrador = new Administrador();
            novoAdministrador.setNome(nome);
            novoAdministrador.setEmail(email);
            novoAdministrador.setCpf(cpf);
            novoAdministrador.setSenha(senha);
            novoAdministrador.setTelefone(telefone); 
            novoAdministrador.setDataNascimento(dataNascimento); 
            novoAdministrador.setEndereco(endereco); 
            novoAdministrador.setDataAdmissao(dataAdmissao); 

            administradorService.save(novoAdministrador);
            
            redirectAttributes.addFlashAttribute("mensagem", "Administrador cadastrado com sucesso!");
            return "redirect:/main";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar administrador: " + e.getMessage());
            return "redirect:/cadastroAdministrador";
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/loginAdministrador")
    public String showLoginForm(Model model) {
        model.addAttribute("title", "Login de Administrador");
        model.addAttribute("content", "LoginAdministrador :: content");
        return "LoginAdministrador";
    }

    @PostMapping("/loginAdministrador")
    public String login(@RequestParam String email, @RequestParam String senha, Model model) {
        Administrador administrador = administradorService.findByEmailAndSenha(email, senha);

        if (administrador != null) {
            // Login bem-sucedido
            model.addAttribute("administrador", administrador);
            return "Layout3";
        } else {
            // Login falhou
            model.addAttribute("erro", "Email ou senha inválidos");
            return "LoginAdministrador";
        }
    }
}
