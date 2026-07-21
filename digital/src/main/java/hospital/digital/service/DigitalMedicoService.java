package hospital.digital.service;

import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.medico.MedicoNaoEncontradoException;
import hospital.digital.repository.MedicoDAO;
import hospital.digital.web.dto.medico.response.ResponseMedicoQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DigitalMedicoService {

    /*TODO: features:
        método de query personalizada.
     */

    public MedicoDAO medicoDAO;

    public DigitalMedicoService(MedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }

    public ResponseMedicoQueryDTO getMedicoById(Long id){
        Medico medico = medicoDAO.findById(id).orElseThrow(() -> new MedicoNaoEncontradoException(id));
        return new ResponseMedicoQueryDTO(medico.getNome(),
                medico.getEspecialidade());
    }
    public List<ResponseMedicoQueryDTO> getMedicosByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Medico> medicos = medicoDAO.getMedicosByPage(pageable);
        return medicos.stream().map(medico -> new ResponseMedicoQueryDTO(
                medico.getNome(),
                medico.getEspecialidade()
        ))
                .collect(Collectors.toList());
    }



}
