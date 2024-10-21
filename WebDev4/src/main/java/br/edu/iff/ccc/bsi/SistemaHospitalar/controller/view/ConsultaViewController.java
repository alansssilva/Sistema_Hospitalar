package br.edu.iff.ccc.bsi.SistemaHospitalar.controller.view;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Comum;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Consulta;
import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.Medico;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ConsultaService;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.ComumService;
import br.edu.iff.ccc.bsi.SistemaHospitalar.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ConsultaViewController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ComumService comumService;

    @GetMapping("/crudConsulta")
    public String crudConsultaView() {
        return "redirect:/listarMedicosEPacientes";
    }

    @GetMapping("/listarMedicosEPacientes")
    public ModelAndView listarMedicosEPacientes() {
        List<Medico> medicos = medicoService.findAll();
        List<Comum> pacientes = comumService.findAll();

        ModelAndView modelAndView = new ModelAndView("CRUD_Consulta");
        modelAndView.addObject("medicos", medicos);
        modelAndView.addObject("pacientes", pacientes);

        return modelAndView;
    }

    @PostMapping("/removerMedico/{id}")
    public String removerMedico(@PathVariable("id") Integer id, RedirectAttributes ra) {
        medicoService.deleteById(id);
        ra.addFlashAttribute("success", "Médico removido com sucesso.");
        return "redirect:/listarMedicosEPacientes";
    }

    @PostMapping("/removerPaciente/{id}")
    public String removerPaciente(@PathVariable("id") Integer id, RedirectAttributes ra) {
        comumService.deleteById(id);
        ra.addFlashAttribute("success", "Paciente removido com sucesso.");
        return "redirect:/listarMedicosEPacientes";
    }
}

