package com.gabriel.system_user.service;

import com.gabriel.system_user.entity.User;
import com.gabriel.system_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> findUsers() {
        return repository.findAll();
    }

    public Optional<User> idUser(Long id) {
        return repository.findById(id);
    }
}
