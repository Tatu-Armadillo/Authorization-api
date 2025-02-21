package br.com.fiap.hackaton.authorization.configuration.security;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.hackaton.authorization.excepetion.BusinessException;
import br.com.fiap.hackaton.authorization.mock.UserMock;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.tokenService = new TokenService("secret");
    }

    @Test
    void whenCreateTokenReturnToken() {
        final var response = this.tokenService.createToken(UserMock.mock());
        assertNotNull(response);
    }

    @Test
    void whenCreateTokenFailedReturnBusinessExcpetion() {
        final var userFalied = UserMock.mock();
        userFalied.setPermissions(null);
        userFalied.setPerson(null);
        final var error = assertThrows(BusinessException.class,
                () -> this.tokenService.createToken(userFalied));

        assertEquals("Error to generate Token JWT", error.getMessage());
    }

    @Test
    void shouldReturnSubjectWhenTokenIsValid() {
        final var token = JWT.create()
                .withIssuer("authorization_hackaton")
                .withSubject("user")
                .sign(Algorithm.HMAC256("secret"));

        final var subject = this.tokenService.getSubject(token);

        assertNotNull(subject);
    }

    @Test
    void whenGetSubjectFailedReturnError() {
        final var error = assertThrows(BusinessException.class,
                () -> this.tokenService.getSubject("teste"));
        assertEquals("Token JWT Invalid or expired", error.getMessage());
    }
}
