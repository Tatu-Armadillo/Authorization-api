package br.com.fiap.hackaton.authorization.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.fiap.hackaton.authorization.mock.CreateCredentialsDtoMock;
import br.com.fiap.hackaton.authorization.mock.PersonMock;
import br.com.fiap.hackaton.authorization.mock.UserMock;
import br.com.fiap.hackaton.authorization.model.Permission;
import br.com.fiap.hackaton.authorization.repository.UserRepository;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PermissionService permissionService;

    @Mock
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserService(userRepository, permissionService, personService);
    }

    @Test
    void whenCreateUserReturnNewUser() {
        when(this.permissionService.getPermission(anyBoolean())).thenReturn(new Permission(""));
        when(this.personService.save(any())).thenReturn(PersonMock.mock());
        when(this.userRepository.save(any())).thenReturn(UserMock.mock());

        final var response = this.userService.create(CreateCredentialsDtoMock.mock());

        assertNotNull(response);
    }

    @Test
    void whenLoadUserByUsernameReturnUser() {
        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.of(UserMock.mock()));

        final var response = this.userService.loadUserByUsername("");

        assertNotNull(response);
    }

    @Test
    void whenLoadUserByUsernameFailedReturnError() {
        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        final var error = assertThrows(UsernameNotFoundException.class,
                () -> this.userService.loadUserByUsername("test"));

        assertEquals("Username test not found!", error.getMessage());
    }
}
