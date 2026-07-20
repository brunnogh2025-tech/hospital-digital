package hospital.digital.service;

import hospital.digital.entity.paciente.Paciente;
import hospital.digital.entity.paciente.PacienteNaoEncontradoException;
import hospital.digital.repository.PacienteDAO;
import hospital.digital.web.dto.paciente.request.RequestPacienteCadastroDTO;
import hospital.digital.web.dto.paciente.request.RequestPacienteUpdateDTO;
import hospital.digital.web.dto.paciente.response.ResponsePacienteQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DigitalPacienteService {

    /*TODO: features:
        consultar por id;
        consultar todos;
        consulta paginada;
        mudar as informações de perfil;
        deletar registro;
        Adicionar o tratamento de exceções a cada um dos métodos.
     */

    PacienteDAO pacienteDAO;

    public DigitalPacienteService(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public ResponsePacienteQueryDTO getPacienteById(Long id){
        Paciente paciente = pacienteDAO.findById(id).orElseThrow(() -> PacienteNaoEncontradoException.porId(id));
        return new ResponsePacienteQueryDTO(
                paciente.getNome(),
                paciente.calcularIdade(),
                paciente.getData_nasc(),
                paciente.getSintomas()
        );
    }

    public List<ResponsePacienteQueryDTO> getPacientesByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Paciente> pacientes = pacienteDAO.findAll(pageable);
        ArrayList<ResponsePacienteQueryDTO> pacienteQueryDTOs = new ArrayList<ResponsePacienteQueryDTO>();
        return pacientes.stream()
                .map(paciente -> new ResponsePacienteQueryDTO(
                        paciente.getNome(),
                        paciente.calcularIdade(),
                        paciente.getData_nasc(),
                        paciente.getSintomas()
                )).collect(Collectors.toList());
    }

    public void savePaciente(RequestPacienteCadastroDTO pacienteCadastroDTO){
        Paciente paciente = Paciente.builder()
                .nome(pacienteCadastroDTO.nome())
                .data_nasc(pacienteCadastroDTO.dataNasc())
                .cpf(pacienteCadastroDTO.cpf())
                .email(pacienteCadastroDTO.email())
                .senha(pacienteCadastroDTO.senha())
                .telefone(pacienteCadastroDTO.telefone())
                .sintomas(pacienteCadastroDTO.sintomas())
                .build();
        pacienteDAO.save(paciente);
    }

    public void setPaciente(RequestPacienteUpdateDTO pacienteUpdateDTO, Long id){
        Paciente paciente = Paciente.builder()
                .cpf(pacienteUpdateDTO.cpf())
                .senha(pacienteUpdateDTO.senha())
                .email(pacienteUpdateDTO.email())
                .telefone(pacienteUpdateDTO.telefone())
                .nome(pacienteUpdateDTO.nome())
                .data_nasc(pacienteUpdateDTO.dataNasc())
                .sintomas(pacienteUpdateDTO.sintomas())
                .build();
        paciente.setId(id);
        pacienteDAO.save(paciente);
    }

    public void deletePaciente(Long id){
        pacienteDAO.deleteById(id);
    }


}
