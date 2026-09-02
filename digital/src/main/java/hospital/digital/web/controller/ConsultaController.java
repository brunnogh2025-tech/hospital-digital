package hospital.digital.web.controller;


import hospital.digital.service.DigitalConsultaService;
import hospital.digital.web.dto.consulta.request.RequestAgendamentoConsultaDTO;
import hospital.digital.web.dto.consulta.request.RequestConsultaUpdateDTO;
import hospital.digital.web.dto.consulta.response.ResponseConsultaQueryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/consulta")
public class ConsultaController {
    
    public final DigitalConsultaService consultaService;

    public ConsultaController(DigitalConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ResponseConsultaQueryDTO> getConsultaById(@PathVariable Long id){
        ResponseConsultaQueryDTO consultaDTO = consultaService.getConsultaById(id);
        if(consultaDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(consultaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping(path = "/page/{page}")
    public ResponseEntity<List<ResponseConsultaQueryDTO>> getConsultasByPage(@RequestParam int page){
        List<ResponseConsultaQueryDTO> consultasDTO = consultaService.getConsultasByPage(page);
        return ResponseEntity.status(HttpStatus.OK).body(consultasDTO);
    }
    @PostMapping(path = "/save")
    public ResponseEntity<RequestAgendamentoConsultaDTO> saveConsulta(@RequestParam RequestAgendamentoConsultaDTO agendamento){
        consultaService.saveConsulta(agendamento);
        return ResponseEntity.status(HttpStatus.OK).body(agendamento);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<RequestConsultaUpdateDTO> updateConsulta(@RequestParam RequestConsultaUpdateDTO dados_A_Atualizar, @PathVariable Long id){
        consultaService.setConsulta(dados_A_Atualizar, id);
        return ResponseEntity.status(HttpStatus.OK).body(dados_A_Atualizar);
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("#id == authentication.principal.id or )")
    public ResponseEntity<ResponseConsultaQueryDTO> deleteConsulta(@PathVariable Long id){
        ResponseConsultaQueryDTO consultaDTO = consultaService.getConsultaById(id);
        if (consultaDTO != null){
            consultaService.deleteConsulta(id);
            return ResponseEntity.status(HttpStatus.OK).body(consultaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
