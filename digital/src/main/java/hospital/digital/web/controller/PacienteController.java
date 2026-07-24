package hospital.digital.web.controller;

import hospital.digital.service.DigitalPacienteService;
import hospital.digital.web.dto.paciente.response.ResponsePacienteQueryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    /*
    TODO: Mensagens personalizadas
    */

    public final DigitalPacienteService pacienteService;

    public PacienteController(DigitalPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponsePacienteQueryDTO> getPacienteById(@PathVariable Long id){
        ResponsePacienteQueryDTO pacienteDTO = pacienteService.getPacienteById(id);
        if(pacienteDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(pacienteDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
