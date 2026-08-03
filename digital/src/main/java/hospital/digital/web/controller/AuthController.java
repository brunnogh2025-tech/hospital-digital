package hospital.digital.web.controller;

import hospital.digital.configuration.TokenConfig;
import hospital.digital.service.DigitalMedicoService;
import hospital.digital.service.DigitalPacienteService;
import hospital.digital.web.dto.UsuarioLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    public final DigitalPacienteService pacienteService;
    public final DigitalMedicoService medicoService;
    public final PasswordEncoder passwordEncoder;
    public final AuthenticationManager authenticationManager;
    public final TokenConfig tokenConfig;

    public AuthController(DigitalPacienteService pacienteService, DigitalMedicoService medicoService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    /*@PostMapping("/login")
    public ResponseEntity<UserDetails> loadByUsername(UsuarioLoginDTO usuarioLoginDTO){

    }*/
}
