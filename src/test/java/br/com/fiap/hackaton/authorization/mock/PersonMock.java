package br.com.fiap.hackaton.authorization.mock;

import br.com.fiap.hackaton.authorization.model.Person;

public class PersonMock {
    public static Person mock() {
        final var person = new Person();
        person.setId(1L);
        person.setAge(1);
        person.setContact("contact");
        person.setCpf("cpf");
        person.setFullName("fullname");
        person.setGender("gender");
        return person;
    }

}
