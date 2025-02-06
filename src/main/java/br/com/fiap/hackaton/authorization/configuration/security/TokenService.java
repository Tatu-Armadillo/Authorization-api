package br.com.fiap.hackaton.authorization.configuration.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.fiap.hackaton.authorization.excepetion.BusinessException;
import br.com.fiap.hackaton.authorization.model.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(final User user) {
        try {
            final var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("authorization_hackaton")
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new BusinessException("Error to generate Token JWT");
        }
    }

    public String getSubject(final String tokenJWT) {
        try {
            final var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("authorization_hackaton")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (Exception e) {
            throw new BusinessException("Token JWT Invalid or expired");
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(10)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
