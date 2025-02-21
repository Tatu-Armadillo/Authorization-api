package br.com.fiap.hackaton.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.hackaton.authorization.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
