package br.com.fiap.hackaton.authorization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.hackaton.authorization.model.Person;
import br.com.fiap.hackaton.authorization.repository.PersonRepository;

@Service
public class PersonService {
    
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(final Person person) {
        return this.personRepository.save(person);
    }

}
