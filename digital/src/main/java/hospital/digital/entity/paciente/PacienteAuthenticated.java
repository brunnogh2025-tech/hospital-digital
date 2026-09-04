package hospital.digital.entity.paciente;

import hospital.digital.entity.UserDetailsWithId;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class PacienteAuthenticated implements UserDetailsWithId {
    public Paciente paciente;

    public PacienteAuthenticated(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.paciente.getRoles());
    }

    @Override
    public String getPassword() {
        return this.paciente.getSenha();
    }

    @Override
    public String getUsername() {
        return this.paciente.getEmail();
    }

    @Override
    public Long getId() {
        return paciente.getId();
    }
}
