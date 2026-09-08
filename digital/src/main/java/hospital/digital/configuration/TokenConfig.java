package hospital.digital.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import hospital.digital.entity.UserDetailsWithId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@ConfigurationProperties(prefix = "jwt")
@Slf4j
public class TokenConfig {

    String secret;
    Long expiration;
    String issuer;

    Algorithm algorithm = Algorithm.HMAC256(secret);

    public String generateToken(UserDetailsWithId user){


        return JWT.create()
                .withClaim("UserId", user.getId())
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(expiration))
                .withIssuer(issuer)
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public boolean isTokenValid(String jwt){
        try {
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            log.warn("Token inválido: {}", e.getMessage());
            return false;
        }

    }

    public String getUsernameFromToken(String jwt){
        return JWT.require(algorithm)
                .build()
                .verify(jwt)
                .getSubject();
    }


}
