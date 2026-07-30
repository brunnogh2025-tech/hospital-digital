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
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DigitalConsultaService {

    /*TODO: features:
        método de query personalizada.
     */

    public final ConsultaDAO consultaDAO;

    public DigitalConsultaService(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    public ResponseConsultaQueryDTO getConsultaById(Long id){
        log.info("Buscando consulta pelo id {}",id);
        Consulta consulta = consultaDAO.findById(id).orElseThrow(() -> new ConsultaNaoEncontradaException(id));
        return new ResponseConsultaQueryDTO(
                consulta.getPaciente().getNome(),
                consulta.getData(),
                consulta.getMedico().getNome());
    }

    public List<ResponseConsultaQueryDTO> getConsultasByPage(int page){
        log.info("Buscando consultas na página {}",page);
        Pageable pageable = PageRequest.of(page, 100);
        Page<Consulta> consultas = consultaDAO.getConsultaPageable(pageable);
        return consultas.stream()
                .map(consulta -> new ResponseConsultaQueryDTO(
                        consulta.getPaciente().getNome(),
                        consulta.getData(),
                        consulta.getMedico().getNome()
                )).collect(Collectors.toList());

    }
    @Transactional
    public void saveConsulta(RequestAgendamentoConsultaDTO agendamento){
        log.info("Salvando consulta com os dados: {}",agendamento);
        Consulta consulta = Consulta.builder()
                .paciente(agendamento.paciente())
                .data(agendamento.dataAgendada())
                .medico(agendamento.medico())
                .build();
        consultaDAO.save(consulta);
    }
    @Transactional
    public void setConsulta(RequestConsultaUpdateDTO consultaUpdate, Long id){
        log.info("Mudando consulta com o id {} com os dados: {}",id, consultaUpdate);
        Consulta consulta = Consulta.builder()
                .paciente(consultaUpdate.paciente())
                .data(consultaUpdate.data())
                .medico(consultaUpdate.medico())
                .build();
        consulta.setId(id);
        consultaDAO.save(consulta);

    }
    @Transactional
    public void deleteConsulta(Long id){
        log.info("Deletando consulta com o id {}",id);
        consultaDAO.deleteById(id);
    }


}
