package hospital.digital.service;

import hospital.digital.entity.paciente.Paciente;
import hospital.digital.repository.ConsultaDAO;
import hospital.digital.repository.MedicoDAO;
import hospital.digital.repository.PacienteDAO;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;

@Service
public class DigitalService {

    ConsultaDAO consultaDAO;
    MedicoDAO medicoDAO;
    PacienteDAO pacienteDAO;

    public DigitalService(ConsultaDAO consultaDAO, MedicoDAO medicoDAO, PacienteDAO pacienteDAO) {
        this.consultaDAO = consultaDAO;
        this.medicoDAO = medicoDAO;
        this.pacienteDAO = pacienteDAO;
    }

    public Paciente getPacienteById(Long id){
        return pacienteDAO.findById(id).orElseThrow(() -> new FindException());
    }

}
