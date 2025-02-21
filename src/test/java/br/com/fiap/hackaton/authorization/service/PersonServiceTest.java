package br.com.fiap.hackaton.authorization.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackaton.authorization.mock.PersonMock;
import br.com.fiap.hackaton.authorization.repository.PersonRepository;

class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.personService = new PersonService(personRepository);
    }

    @Test
    void whenCreatePersonReturnPerson() {
        when(this.personRepository.save(any())).thenReturn(PersonMock.mock());

        final var response = this.personService.save(PersonMock.mock());

        assertNotNull(response);

    }
}
