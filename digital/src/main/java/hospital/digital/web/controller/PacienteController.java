package hospital.digital.web.controller;

import hospital.digital.service.DigitalPacienteService;
import hospital.digital.web.dto.paciente.request.RequestPacienteQueryDTO;
import hospital.digital.web.dto.paciente.request.RequestPacienteUpdateDTO;
import hospital.digital.web.dto.paciente.response.ResponsePacienteQueryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/paciente")
public class PacienteController {
    /*
    TODO: Mensagens personalizadas
    */

    public final DigitalPacienteService pacienteService;

    public PacienteController(DigitalPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ResponsePacienteQueryDTO> getPacienteById(@PathVariable Long id){
        ResponsePacienteQueryDTO pacienteDTO = pacienteService.getPacienteById(id);
        if(pacienteDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(pacienteDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping(path = "/page")
    public ResponseEntity<List<ResponsePacienteQueryDTO>> getPacientesByPage(@RequestParam int page){
        List<ResponsePacienteQueryDTO> pacientesDTO = pacienteService.getPacientesByPage(page);
        return ResponseEntity.status(HttpStatus.OK).body(pacientesDTO);
    }

    @GetMapping("/pageFiltered/{page}")
    public ResponseEntity<List<ResponsePacienteQueryDTO>> getPacientesByPageFiltered(
            @RequestParam byte idade, @RequestParam String nome, @RequestParam int page){
        List<ResponsePacienteQueryDTO> pacientesDTO = pacienteService.getPacientesByPageFiltered(idade, nome, page);
        return ResponseEntity.status(HttpStatus.OK).body(pacientesDTO);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<RequestPacienteUpdateDTO> updatePaciente(@RequestParam RequestPacienteUpdateDTO dados_A_Atualizar, @PathVariable Long id){
        pacienteService.setPaciente(dados_A_Atualizar, id);
        return ResponseEntity.status(HttpStatus.OK).body(dados_A_Atualizar);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ResponsePacienteQueryDTO> deletePaciente(@PathVariable Long id){
        ResponsePacienteQueryDTO pacienteDTO = pacienteService.getPacienteById(id);
        if (pacienteDTO != null){
            pacienteService.deletePaciente(id);
            return ResponseEntity.status(HttpStatus.OK).body(pacienteDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


}
