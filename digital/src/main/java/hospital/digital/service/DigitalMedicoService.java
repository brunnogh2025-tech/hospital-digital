package hospital.digital.service;

import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.medico.MedicoNaoEncontradoException;
import hospital.digital.entity.paciente.Paciente;
import hospital.digital.repository.MedicoDAO;
import hospital.digital.web.dto.medico.request.RequestMedicoCadastroDTO;
import hospital.digital.web.dto.medico.response.ResponseMedicoQueryDTO;
import hospital.digital.web.dto.paciente.response.ResponsePacienteQueryDTO;
import jakarta.transaction.Transactional;
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
public class DigitalMedicoService {

    /*TODO: features:
        método de query personalizada.
     */

    public MedicoDAO medicoDAO;

    public DigitalMedicoService(MedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }

    public ResponseMedicoQueryDTO getMedicoById(Long id){
        log.info("Buscando médico com id {}",id);
        Medico medico = medicoDAO.findById(id).orElseThrow(() -> new MedicoNaoEncontradoException(id));
        return new ResponseMedicoQueryDTO(
                medico.getNome(),
                medico.getEspecialidade(),
                medico.getData_nasc());
    }
    public List<ResponseMedicoQueryDTO> getMedicosByPage(int page){
        log.info("Buscando medicos na página {}", page);
        Pageable pageable = PageRequest.of(page, 10);
        Page<Medico> medicos = medicoDAO.getMedicosByPage(pageable);
        return medicos.stream().map(medico -> new ResponseMedicoQueryDTO(
                medico.getNome(),
                medico.getEspecialidade(),
                medico.getData_nasc()
        ))
                .collect(Collectors.toList());
    }
    public List<ResponseMedicoQueryDTO> getMedicosByPageFiltered(byte idade, String nome, int page){
        log.info("Encontrando médicos por pesquisa filtrada, com idade {}, nome {}, pagina {}", idade, nome, page);
        Pageable pageable = PageRequest.of(page, 10);
        LocalDate dataMin = LocalDate.now().minusYears(idade);
        LocalDate dataMax = dataMin.minusYears(1).plusDays(1);
        Page<Medico> medicos = medicoDAO.getAllByPageFilter(dataMax, dataMin, nome, pageable);
        return medicos.stream()
                .map(medico -> new ResponseMedicoQueryDTO(
                        medico.getNome(),
                        medico.getEspecialidade(),
                        medico.getData_nasc()
                )).collect(Collectors.toList());

    }

    @Transactional
    public void saveMedico(RequestMedicoCadastroDTO medicoCadastroDTO){
        log.info("Salvando médico com os dados: {}", medicoCadastroDTO);
        Medico medico = Medico.builder()
                .nome(medicoCadastroDTO.nome())
                .especialidade(medicoCadastroDTO.especialidade())
                .data_nasc(medicoCadastroDTO.data_nasc())
                .cpf(medicoCadastroDTO.cpf())
                .email(medicoCadastroDTO.email())
                .senha(medicoCadastroDTO.senha())
                .telefone(medicoCadastroDTO.telefone())
                .crm(medicoCadastroDTO.crm())
                .build();
        medicoDAO.save(medico);
    }

    @Transactional
    public void setMedico(RequestMedicoCadastroDTO medicoCadastroDTO, Long id){
        log.info("Atualizando medico com o id {} e os dados: {}",id, medicoCadastroDTO);
        Medico medico = Medico.builder()
                .nome(medicoCadastroDTO.nome())
                .especialidade(medicoCadastroDTO.especialidade())
                .data_nasc(medicoCadastroDTO.data_nasc())
                .cpf(medicoCadastroDTO.cpf())
                .email(medicoCadastroDTO.email())
                .senha(medicoCadastroDTO.senha())
                .telefone(medicoCadastroDTO.telefone())
                .crm(medicoCadastroDTO.crm())
                .build();
        medico.setId(id);
        medicoDAO.save(medico);
    }

    @Transactional
    public void deleteMedico(Long id)
    {
        log.info("Deletando o medico com o id {}",id);
        medicoDAO.deleteById(id);
    }



}
