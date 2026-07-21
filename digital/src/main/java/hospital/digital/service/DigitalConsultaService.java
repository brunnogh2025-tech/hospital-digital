package hospital.digital.service;

import hospital.digital.entity.consulta.Consulta;
import hospital.digital.entity.consulta.ConsultaNaoEncontradaException;
import hospital.digital.entity.paciente.Paciente;
import hospital.digital.repository.ConsultaDAO;
import hospital.digital.web.dto.consulta.request.RequestAgendamentoConsultaDTO;
import hospital.digital.web.dto.consulta.request.RequestConsultaUpdateDTO;
import hospital.digital.web.dto.consulta.request.RequestQueryConsultaDTO;
import hospital.digital.web.dto.consulta.response.ResponseConsultaQueryDTO;
import hospital.digital.web.dto.paciente.response.ResponsePacienteQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DigitalConsultaService {

    /*TODO: features:
        método de query personalizada.
     */

    public final ConsultaDAO consultaDAO;

    public DigitalConsultaService(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    public ResponseConsultaQueryDTO getConsultaById(Long id){
        Consulta consulta = consultaDAO.findById(id).orElseThrow(() -> new ConsultaNaoEncontradaException(id));
        return new ResponseConsultaQueryDTO(
                consulta.getPaciente().getNome(),
                consulta.getData(),
                consulta.getMedico().getNome());
    }

    public List<ResponseConsultaQueryDTO> getConsultasByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Consulta> consultas = consultaDAO.getConsultaPageable(pageable);
        return consultas.stream()
                .map(consulta -> new ResponseConsultaQueryDTO(
                        consulta.getPaciente().getNome(),
                        consulta.getData(),
                        consulta.getMedico().getNome()
                )).collect(Collectors.toList());

    }
    public void saveConsulta(RequestAgendamentoConsultaDTO agendamento){
        Consulta consulta = Consulta.builder()
                .paciente(agendamento.paciente())
                .data(agendamento.dataAgendada())
                .medico(agendamento.medico())
                .build();
        consultaDAO.save(consulta);
    }

    public void setConsulta(RequestConsultaUpdateDTO consultaUpdate, Long id){
        Consulta consulta = Consulta.builder()
                .paciente(consultaUpdate.paciente())
                .data(consultaUpdate.data())
                .medico(consultaUpdate.medico())
                .build();
        consulta.setId(id);
        consultaDAO.save(consulta);

    }

    public void deleteConsulta(Long id){
        consultaDAO.deleteById(id);
    }
}
