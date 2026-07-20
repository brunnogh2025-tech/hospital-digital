package hospital.digital.service;

import hospital.digital.entity.paciente.Paciente;
import hospital.digital.entity.paciente.PacienteNaoEncontradoException;
import hospital.digital.repository.PacienteDAO;
import hospital.digital.web.dto.paciente.response.ResponsePacienteQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigitalPacienteService {

    /*TODO: features:
        consultar por id;
        consultar todos;
        consulta paginada;
        mudar as informações de perfil;
        deletar registro.
     */

    PacienteDAO pacienteDAO;

    public DigitalPacienteService(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public ResponsePacienteQueryDTO getPacienteById(Long id){
        Paciente paciente = pacienteDAO.findById(id).orElseThrow(() -> PacienteNaoEncontradoException.porId(id));
        return new ResponsePacienteQueryDTO(
                paciente.getNome(),
                paciente.getIdade(),
                paciente.getData_nasc(),
                paciente.getSintomas()
        );
    }

    public List<ResponsePacienteQueryDTO> getPacientesPageable(int pagina, int quantidade){
        Pageable pageable = PageRequest.of(pagina, quantidade);
        Page<Paciente> pacientes = pacienteDAO.findAll(pageable);
        ArrayList<ResponsePacienteQueryDTO> pacienteQueryDTOs = new ArrayList<ResponsePacienteQueryDTO>();
        for(Paciente paciente : pacientes){
            pacienteQueryDTOs.add(new ResponsePacienteQueryDTO(
                    paciente.getNome(),
                    paciente.getIdade(),
                    paciente.getData_nasc(),
                    paciente.getSintomas()));
        }
        return pacienteQueryDTOs;
    }

    public void savePaciente(Paciente paciente){
        pacienteDAO.save(paciente);
    }

    public void setPaciente(Paciente paciente, Long id){
        paciente.setId(id);
        pacienteDAO.save(paciente);
    }



}
