package hospital.digital.service;

import hospital.digital.entity.paciente.Paciente;
import hospital.digital.entity.paciente.PacienteNaoEncontradoException;
import hospital.digital.repository.ConsultaDAO;
import hospital.digital.repository.MedicoDAO;
import hospital.digital.repository.PacienteDAO;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

@Service
public class DigitalService {

    PacienteDAO pacienteDAO;

    public DigitalService(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public Paciente getPacienteById(Long id){
        return pacienteDAO.findById(id).orElseThrow(() -> PacienteNaoEncontradoException.porId(id));
    }

    public List<Paciente> getPacientes(){
        return pacienteDAO.findAll();
    }

    public void savePaciente(Paciente paciente){
        pacienteDAO.save(paciente);
    }

    public void setPaciente(Paciente paciente, Long id){
        paciente.setId(id);
        pacienteDAO.save(paciente);
    }

}
