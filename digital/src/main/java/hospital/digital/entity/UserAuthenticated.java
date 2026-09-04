package hospital.digital.entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserAuthenticated implements UserDetailsWithId{

    private final Usuario usuario;

    public UserAuthenticated(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Long getId() {
        return usuario.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(usuario.getRoles());
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
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
}
