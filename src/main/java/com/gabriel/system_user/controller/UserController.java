package com.gabriel.system_user.controller;

import com.gabriel.system_user.DTO.Reponse.UserResponse;
import com.gabriel.system_user.DTO.UserDTO;
import com.gabriel.system_user.entity.User;
import com.gabriel.system_user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDTO userDTO) {

        User user = new User(null, userDTO.name(), userDTO.email(), userDTO.password(), userDTO.cpf(), userDTO.birthdate(), true);

        User savedUser = service.saveUser(user);

        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserResponse>> listUsers() {

        List<User> findUserByList = service.findUsers();

        List<UserResponse> responseList = findUserByList.stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}
