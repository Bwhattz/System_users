package com.gabriel.system_user.DTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserDTO(

        @NotBlank(message = "Nome não pode ser nulo")
        String name,

        @Email(message = "O email precisa ser obrigatorio")
        @NotBlank(message = "O email não pode ser nulo")
        String email,

        @NotBlank(message = "A senha não pode ser nula")
        @Size(min = 6, message = "A senha precisa conter pelo menos 6 caracteres")
        String password,

        @NotBlank(message = "O cpf não pode ser nulo")
        @Pattern(regexp = "\\d{11}", message = "O cpf precisa conter 11 digitos")
        String cpf,

        @NotNull(message = "O nascimento precisa ser obrigatório")
        @Past(message = "A data de nascimento precisa ser no passado")
        LocalDate birthdate
) {
}
