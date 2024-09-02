package br.edu.iff.ccc.bsi.SistemaHospitalar;

import br.edu.iff.ccc.bsi.SistemaHospitalar.entities.*;
import br.edu.iff.ccc.bsi.SistemaHospitalar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ComumRepository comumRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void run(String... args) throws Exception {

        Administrador admin = new Administrador();
        admin.setNome("Alan Souza");
        admin.setEmail("Alanss@gmail.com");
        admin.setSenha("admin123");
        admin.setTipo("Administrador");
        admin.setCpf("12345678901");
        admin.setTelefone("123456789");
        admin.setDataNascimento(LocalDateTime.of(1980, 1, 1, 0, 0));
        admin.setEndereco("123 Main St");
        admin.setDataAdmissao(LocalDateTime.now());

        Medico medico = new Medico();
        medico.setNome("Dr. Jane Smith");
        medico.setEmail("jane.smith@outlook.com");
        medico.setSenha("medico123");
        medico.setTipo("Medico");
        medico.setCpf("98765432100");
        medico.setTelefone("987654321");
        medico.setDataNascimento(LocalDateTime.of(1975, 5, 15, 0, 0));
        medico.setEndereco("456 Elm St");
        medico.setEspecialidade("Cardiologia");
        medico.setCRM("123456");

        Comum paciente = new Comum();
        paciente.setNome("Alice Johnson");
        paciente.setEmail("alice.johnson@gmail.com");
        paciente.setSenha("paciente123");
        paciente.setTipo("Comum");
        paciente.setCpf("11122233344");
        paciente.setTelefone("1122334455");
        paciente.setDataNascimento(LocalDateTime.of(1990, 7, 20, 0, 0));
        paciente.setEndereco("789 Pine St");
        paciente.setPossuiPlanoSaude(true);

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setData(LocalDateTime.now().plusDays(7));
        consulta.setDescricao("Consulta de rotina");
        consulta.setPaciente(paciente);


        administradorRepository.save(admin);
        medicoRepository.save(medico);
        comumRepository.save(paciente);
        consultaRepository.save(consulta);


        System.out.println("Dados de administradores:");
        administradorRepository.findAll().forEach(a -> System.out.println(a.getNome()));

        System.out.println("Dados de médicos:");
        medicoRepository.findAll().forEach(m -> System.out.println(m.getNome()));

        System.out.println("Dados de pacientes:");
        comumRepository.findAll().forEach(p -> System.out.println(p.getNome()));

        System.out.println("Dados de consultas:");
        consultaRepository.findAll().forEach(c -> {
            System.out.println("Consulta: " + c.getDescricao() + " com o Dr. " + c.getMedico().getNome() + " para " + c.getPaciente().getNome());
        });
    }
}
