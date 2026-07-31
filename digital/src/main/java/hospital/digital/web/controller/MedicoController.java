package hospital.digital.web.controller;

import hospital.digital.service.DigitalMedicoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/medico")
public class MedicoController {

    DigitalMedicoService medicoService;

    public MedicoController(DigitalMedicoService medicoService) {
        this.medicoService = medicoService;
    }


}
