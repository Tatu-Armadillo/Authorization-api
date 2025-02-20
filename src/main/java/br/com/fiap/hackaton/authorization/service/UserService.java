package br.com.fiap.hackaton.authorization.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.hackaton.authorization.model.User;
import br.com.fiap.hackaton.authorization.record.CreateCredentialsDto;
import br.com.fiap.hackaton.authorization.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionService permissionService;

    @Autowired
    public UserService(
            final UserRepository userRepository,
            final PermissionService permissionService) {
        this.userRepository = userRepository;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
    }

    public User create(final CreateCredentialsDto data) {
        final var permission = this.permissionService.getPermission(data.isDoctor());
        final var user = new User(
                data.email(),
                data.fullName(),
                data.password(),
                true,
                true,
                true,
                true);
        user.setIdentifier(UUID.randomUUID().toString());
        user.setPermissions(List.of(permission));
        return this.userRepository.save(user);
    }

}
