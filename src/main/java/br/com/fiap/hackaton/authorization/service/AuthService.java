package br.com.fiap.hackaton.authorization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.hackaton.authorization.record.AccountCredentialsDto;
import br.com.fiap.hackaton.authorization.record.CreateCredentialsDto;
import br.com.fiap.hackaton.authorization.record.TokenDto;
import br.com.fiap.hackaton.authorization.configuration.security.TokenService;
import br.com.fiap.hackaton.authorization.model.User;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthService(
            final AuthenticationManager authenticationManager,
            final PasswordEncoder passwordEncoder,
            final UserService userService,
            final TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenDto signin(final AccountCredentialsDto data) {
        final var user = this.userService.loadUserByUsername(data.email());

        if (user == null) {
            throw new BadCredentialsException("Invalid email/password");
        }

        final var authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));

        return new TokenDto(data.email(), tokenService.createToken((User) authentication.getPrincipal()));

    }

    public void create(final CreateCredentialsDto data) {
        final var password = passwordEncoder.encode(data.password());
        this.userService.create(new CreateCredentialsDto(data.email(), data.fullName(), password, data.isDoctor()));
    }

}
