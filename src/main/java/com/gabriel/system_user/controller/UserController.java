package com.gabriel.system_user.controller;

import com.gabriel.system_user.model.User;
import com.gabriel.system_user.request.UserRequest;
import com.gabriel.system_user.response.UserResponse;
import com.gabriel.system_user.service.UserService;
import com.gabriel.system_user.service.jwt.Jwt;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Jwt injectJwt;

    public UserController(UserService userService, Jwt injectJwt) {
        this.userService = userService;
        this.injectJwt = injectJwt;
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) {

        User entity = userRequest.requestEntity();

        User serviceEntity = userService.save(entity);

        String injectGenerateToken = injectJwt.generatedToken(serviceEntity);

        UserResponse userResponse = UserResponse.fromUser(serviceEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Authorization", "Bearer" + injectGenerateToken)
                .body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {

        List<UserResponse> listUser = userService.findAll().stream()
                .map(UserResponse::fromUser)
                .toList();

        return ResponseEntity.ok().body(listUser);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRequest userRequest) {

        User entityUser = userRequest.requestEntity();

        User serviceEntity = userService.update(entityUser);

        String injectGenerateToken = injectJwt.generatedToken(serviceEntity);

        UserResponse userResponse = UserResponse.fromUser(serviceEntity);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Authorization", "Bearer" + injectGenerateToken)
                .body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}