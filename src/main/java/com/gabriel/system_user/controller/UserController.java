package com.gabriel.system_user.controller;

import com.gabriel.system_user.model.User;
import com.gabriel.system_user.request.UserRequest;
import com.gabriel.system_user.response.UserResponse;
import com.gabriel.system_user.service.UserService;
import com.gabriel.system_user.service.jwt.Jwt;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Jwt jwt;

    public UserController(UserService userService, Jwt jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) {

        User entity = userRequest.requestEntity();

        User serviceEntity = userService.save(entity);

        String entityJwt = jwt.generatedToke(serviceEntity);

        UserResponse userResponse = UserResponse.fromUser();

    }
}
