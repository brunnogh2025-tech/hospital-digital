package hospital.digital.web.controller;

import hospital.digital.configuration.TokenConfig;
import hospital.digital.entity.UserDetailsWithId;
import hospital.digital.service.DigitalMedicoService;
import hospital.digital.service.DigitalPacienteService;
import hospital.digital.web.dto.UsuarioLoginDTO;
import hospital.digital.web.dto.medico.request.RequestMedicoCadastroDTO;
import hospital.digital.web.dto.paciente.request.RequestPacienteCadastroDTO;
import hospital.digital.web.dto.ResponseUserRegister;
import hospital.digital.web.dto.paciente.response.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loadByUsername(UsuarioLoginDTO usuarioLoginDTO){
        UsernamePasswordAuthenticationToken datasToCreateToken = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.email(),usuarioLoginDTO.senha());
        Authentication authentication = authenticationManager.authenticate(datasToCreateToken);

        UserDetailsWithId user = (UserDetailsWithId) authentication.getPrincipal();

        assert user != null;
        String token = tokenConfig.generateToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse(token));
    }

    @PostMapping(path = "/register/paciente")
    public ResponseEntity<ResponseUserRegister> registerPaciente(RequestPacienteCadastroDTO request){
        RequestPacienteCadastroDTO pacienteCadastroDTO = new RequestPacienteCadastroDTO(
                request.nome(),
                request.dataNasc(),
                request.email(),
                passwordEncoder.encode(request.senha()),
                request.cpf(),
                request.telefone(),
                request.sintomas()
        );

        pacienteService.savePaciente(pacienteCadastroDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUserRegister(request.nome(), request.email()));
    }

    @PostMapping(path = "/register/medico")
    public ResponseEntity<ResponseUserRegister> registerMedico(RequestMedicoCadastroDTO request){
        RequestMedicoCadastroDTO medicoCadastroDTO = new RequestMedicoCadastroDTO(
                request.nome(),
                request.email(),
                request.telefone(),
                request.cpf(),
                request.data_nasc(),
                request.especialidade(),
                request.crm(),
                passwordEncoder.encode(request.senha())
        );

        medicoService.saveMedico(medicoCadastroDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseUserRegister(request.nome(), request.email()));
    }
}
