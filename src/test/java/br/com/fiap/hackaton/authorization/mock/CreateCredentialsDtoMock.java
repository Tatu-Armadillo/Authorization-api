package br.com.fiap.hackaton.authorization.mock;

import br.com.fiap.hackaton.authorization.record.CreateCredentialsDto;
import br.com.fiap.hackaton.authorization.record.PersonDto;

public class CreateCredentialsDtoMock {
    public static CreateCredentialsDto mock() {
        return new CreateCredentialsDto(
                "email",
                "password",
                true,
                new PersonDto(
                        1,
                        "fullName",
                        "cpf",
                        "gender",
                        "contact",
                        "address"));
    }
}
