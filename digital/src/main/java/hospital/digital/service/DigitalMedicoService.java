package hospital.digital.service;

import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.medico.MedicoNaoEncontradoException;
import hospital.digital.repository.MedicoDAO;
import hospital.digital.web.dto.medico.response.ResponseMedicoQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return new ResponseMedicoQueryDTO(medico.getNome(),
                medico.getEspecialidade());
    }
    public List<ResponseMedicoQueryDTO> getMedicosByPage(int page){
        log.info("Buscando medicos na página {}", page);
        Pageable pageable = PageRequest.of(page, 100);
        Page<Medico> medicos = medicoDAO.getMedicosByPage(pageable);
        return medicos.stream().map(medico -> new ResponseMedicoQueryDTO(
                medico.getNome(),
                medico.getEspecialidade()
        ))
                .collect(Collectors.toList());
    }



}
