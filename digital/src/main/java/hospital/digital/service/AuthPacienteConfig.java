package hospital.digital.service;

import hospital.digital.repository.MedicoDAO;
import hospital.digital.repository.PacienteDAO;
import hospital.digital.web.exception.EntidadeNaoEncontradaException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthPacienteConfig implements UserDetailsService {

    public final PacienteDAO pacienteDAO;
    public final MedicoDAO medicoDAO;

    public AuthPacienteConfig(PacienteDAO pacienteDAO, MedicoDAO medicoDAO) {
        this.pacienteDAO = pacienteDAO;
        this.medicoDAO = medicoDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDetails> usuario = Optional.of(pacienteDAO.findByEmail(email).or(() -> medicoDAO.findByEmail(email))
                .orElseThrow(() -> new EntidadeNaoEncontradaException(("Entidade com o email " + " não encontrada"))));
        return usuario.get();
    }
}
