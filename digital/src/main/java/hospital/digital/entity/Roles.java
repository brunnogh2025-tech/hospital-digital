package hospital.digital.entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    PACIENTE("PACIENTE"),
    MEDICO("MEDICO"),
    ADMIN("ADMIN");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
