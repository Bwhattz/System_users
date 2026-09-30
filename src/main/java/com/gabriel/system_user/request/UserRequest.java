package com.gabriel.system_user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.system_user.model.User;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public record UserRequest(

        @NotBlank(message = "O nome é obrigátorio")
        String name,

        @NotBlank(message = "O sobrenome é obrigátorio")
        String lastName,

        @NotBlank(message = "O email é obrigátorio")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "A senha é obrigátoria")
        @Size(min = 6, message = "A senha precisa conter 11 caracteres")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
                message = "A senha precisa conter pelo menos um caracter maiúscula, uma minúscula e um caracter especial")
        String password,

        @NotBlank(message = "O cpf é obrigátorio")
        String cpf,

        @NotBlank(message = "O número de é obrigátorio")
        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$",

                message = "O formato de telefone deve ser válido: Ex: (81) 99999-9999")
        String telephone,

        @NotNull(message = "A data de nascimento é obrigátorio")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthdate
) {
    public User requestEntity() {

        return User.builder()
                .name(this.name())
                .lastName(this.lastName())
                .email(this.email())
                .password(this.password())
                .cpf(this.cpf())
                .telephone(this.telephone())
                .birthdate(this.birthdate())
                .build();
    }
}
