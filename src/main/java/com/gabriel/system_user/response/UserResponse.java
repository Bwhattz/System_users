package com.gabriel.system_user.response;

import com.gabriel.system_user.model.User;

import java.time.LocalDateTime;

public record UserResponse(
        String name,
        String lastName,
        String email,
        LocalDateTime createdAt,

) {
    public static UserResponse fromUser(User user) {

        return new UserResponse(
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
