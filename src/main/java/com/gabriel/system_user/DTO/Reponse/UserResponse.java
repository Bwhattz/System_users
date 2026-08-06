package com.gabriel.system_user.DTO.Reponse;

import com.gabriel.system_user.entity.User;

public record UserResponse(

        String name,
        String email,
        String cpf,
        Integer age
) {
    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getAge()
        );
    }
}
