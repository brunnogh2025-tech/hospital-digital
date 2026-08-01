package hospital.digital.service;

import hospital.digital.entity.paciente.Paciente;
import hospital.digital.entity.paciente.PacienteNaoEncontradoException;
import hospital.digital.repository.PacienteDAO;
import hospital.digital.web.dto.paciente.request.RequestPacienteCadastroDTO;
import hospital.digital.web.dto.paciente.request.RequestPacienteUpdateDTO;
import hospital.digital.web.dto.paciente.response.ResponsePacienteQueryDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DigitalPacienteService {

    /*TODO:

     */

    public final PacienteDAO pacienteDAO;

    public DigitalPacienteService(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }


    public ResponsePacienteQueryDTO getPacienteById(Long id){
        log.info("Buscando paciente com o id {}", id);
        Paciente paciente = pacienteDAO.findById(id).orElseThrow(() -> new PacienteNaoEncontradoException(id));
        return new ResponsePacienteQueryDTO(
                paciente.getNome(),
                paciente.calcularIdade(),
                paciente.getData_nasc()

        );
    }

    public List<ResponsePacienteQueryDTO> getPacientesByPage(int page){
        log.info("Buscando pacientes na página {}", page);
        Pageable pageable = PageRequest.of(page, 100);
        Page<Paciente> pacientes = pacienteDAO.findAllByPage(pageable);
        return pacientes.stream()
                .map(paciente -> new ResponsePacienteQueryDTO(
                        paciente.getNome(),
                        paciente.calcularIdade(),
                        paciente.getData_nasc()
                )).collect(Collectors.toList());
    }

    public List<ResponsePacienteQueryDTO> getPacientesByPageFiltered(byte idade, String nome, int page){
            log.info("Encontrando pacientes por pesquisa filtrada, com idade {}, nome {}, pagina {}", idade, nome, page);
            Pageable pageable = PageRequest.of(page, 10);
            LocalDate dataMin = LocalDate.now().minusYears(idade);
            LocalDate dataMax = dataMin.minusYears(1).plusDays(1);
            Page<Paciente> pacientes = pacienteDAO.getAllByPageFilter(dataMax, dataMin, nome, pageable);
            return pacientes.stream()
                    .map(paciente -> new ResponsePacienteQueryDTO(
                            paciente.getNome(),
                            paciente.calcularIdade(),
                            paciente.getData_nasc()
                    )).collect(Collectors.toList());

    }

    @Transactional
    public void savePaciente(RequestPacienteCadastroDTO pacienteCadastroDTO){
        log.info("Salvando paciente com os dados: {}", pacienteCadastroDTO);
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

    @Transactional
    public void setPaciente(RequestPacienteUpdateDTO pacienteUpdateDTO, Long id){
        log.info("Atualizando paciente com o id {} e os dados: {}",id, pacienteUpdateDTO);
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

    @Transactional
    public void deletePaciente(Long id)
    {
        log.info("Deletando o paciente com o id {}",id);
        pacienteDAO.deleteById(id);
    }


}
