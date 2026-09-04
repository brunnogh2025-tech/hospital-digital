package hospital.digital.service;

import hospital.digital.entity.UserAuthenticated;
import hospital.digital.entity.UserDetailsWithId;
import hospital.digital.entity.Usuario;
import hospital.digital.repository.MedicoDAO;
import hospital.digital.repository.PacienteDAO;
import hospital.digital.web.exception.EntidadeNaoEncontradaException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserdetailsServiceImpl implements UserDetailsService {

    private final PacienteDAO pacienteDAO;
    private final MedicoDAO medicoDAO;

    public UserdetailsServiceImpl(PacienteDAO pacienteDAO, MedicoDAO medicoDAO) {
        this.pacienteDAO = pacienteDAO;
        this.medicoDAO = medicoDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = pacienteDAO.findByEmail(email)
                .map(p -> (Usuario) p)
                .or(() -> medicoDAO.findByEmail(email).map(m -> (Usuario) m))
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Entidade com o email " + email + " não encontrada"));

        return new UserAuthenticated(usuario);
    }
}
