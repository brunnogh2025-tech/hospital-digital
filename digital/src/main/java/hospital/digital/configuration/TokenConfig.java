package hospital.digital.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hospital.digital.entity.UserDetailsWithId;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ConfigurationProperties(prefix = "jwt")
public class TokenConfig {

    String secret;
    Long expiration;
    String issuer;

    public String generateToken(UserDetailsWithId user){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("UserId", user.getId())
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(expiration))
                .withIssuer(issuer)
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

}
