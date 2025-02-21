package br.com.fiap.hackaton.authorization.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCredentialsDto(
                @NotNull @NotBlank @NotEmpty String email,
                @NotNull @NotBlank @NotEmpty String password,
                @NotNull @NotBlank @NotEmpty boolean isDoctor,
                @NotNull @NotBlank @NotEmpty PersonDto person) {
}
