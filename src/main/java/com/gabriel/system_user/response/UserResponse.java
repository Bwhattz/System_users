package com.gabriel.system_user.response;

import com.gabriel.system_user.model.User;

import java.time.LocalDateTime;

public record UserResponse(
        String idUser,
        String name,
        String lastName,
        String email,
        LocalDateTime createdAt,
        String status

) {
    public static UserResponse fromUser(User user) {

        return new UserResponse(
                user.getIdUser(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getStatus()
        );
    }
}
