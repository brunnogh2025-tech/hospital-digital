package hospital.digital.entity.medico;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MedicoAuthenticated implements UserDetails {

    public Medico medico;

    public MedicoAuthenticated(Medico medico) {
        this.medico = medico;
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
        return List.of(this.medico.getRoles());
    }

    @Override
    public @Nullable String getPassword() {
        return this.medico.getSenha();
    }

    @Override
    public String getUsername() {
        return this.medico.getEmail();
    }
}
