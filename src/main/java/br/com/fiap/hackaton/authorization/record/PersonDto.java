package br.com.fiap.hackaton.authorization.record;

import br.com.fiap.hackaton.authorization.model.Person;

public record PersonDto(
                Integer age,
                String fullName,
                String cpf,
                String gender,
                String contact,
                String address) {

        public static Person toEntity(final PersonDto dto) {
                final var entity = new Person();
                entity.setAge(dto.age());
                entity.setFullName(dto.fullName());
                entity.setCpf(dto.cpf());
                entity.setGender(dto.gender());
                entity.setContact(dto.contact());
                entity.setAddress(dto.address());
                return entity;
        }

}
