package hospital.digital.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsWithId extends UserDetails {
    public abstract Long getId();
}
