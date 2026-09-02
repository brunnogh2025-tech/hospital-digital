package hospital.digital.web.controller;

import hospital.digital.service.DigitalMedicoService;
import hospital.digital.web.dto.medico.request.RequestMedicoCadastroDTO;
import hospital.digital.web.dto.medico.response.ResponseMedicoQueryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/medico")
@PreAuthorize("hasRole('MEDICO')")
public class MedicoController {

    public final DigitalMedicoService medicoService;

    public MedicoController(DigitalMedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ResponseMedicoQueryDTO> getMedicoById(@PathVariable Long id){
        ResponseMedicoQueryDTO medicoDTO = medicoService.getMedicoById(id);
        if(medicoDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(medicoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping(path = "/page")
    public ResponseEntity<List<ResponseMedicoQueryDTO>> getMedicosByPage(@RequestParam int page){
        List<ResponseMedicoQueryDTO> medicosDTO = medicoService.getMedicosByPage(page);
        return ResponseEntity.status(HttpStatus.OK).body(medicosDTO);
    }

    @GetMapping("/pageFiltered/{page}")
    public ResponseEntity<List<ResponseMedicoQueryDTO>> getMedicosByPageFiltered(
            @RequestParam byte idade, @RequestParam String nome, @RequestParam int page){
        List<ResponseMedicoQueryDTO> medicosDTO = medicoService.getMedicosByPageFiltered(idade, nome, page);
        return ResponseEntity.status(HttpStatus.OK).body(medicosDTO);
    }

    @PutMapping(path = "/update/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<RequestMedicoCadastroDTO> updateMedico(@RequestParam RequestMedicoCadastroDTO dados_A_Atualizar, @PathVariable Long id){
        medicoService.setMedico(dados_A_Atualizar, id);
        return ResponseEntity.status(HttpStatus.OK).body(dados_A_Atualizar);
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<ResponseMedicoQueryDTO> deleteMedico(@PathVariable Long id){
        ResponseMedicoQueryDTO medicoDTO = medicoService.getMedicoById(id);
        if (medicoDTO != null){
            medicoService.deleteMedico(id);
            return ResponseEntity.status(HttpStatus.OK).body(medicoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
