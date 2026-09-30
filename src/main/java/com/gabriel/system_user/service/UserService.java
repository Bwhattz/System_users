package com.gabriel.system_user.service;

import com.gabriel.system_user.model.User;
import com.gabriel.system_user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user) {

        validateEmail(user);

        validateCpf(user);

        String securedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(securedPassword);

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void validateEmail(User user) {

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Este usuário ja existe com este email");
        }
    }

    public void validateCpf(User user) {
        if(userRepository.existsByCpf(user.getCpf())) {
            throw new IllegalArgumentException("Este usuário ja existe com esse cpf");
        }
    }

    @Transactional
    public User update(User user) {

        User foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Este usuário não existe"));

        userRepository.findByEmail(user.getEmail()).ifPresent(existsEmailUser -> {
            if(!existsEmailUser.getId().equals(user.getId())) {
                throw new IllegalArgumentException("Este email ja pertence ao outro usuário");
            }
        });

        userRepository.findByCpf(user.getCpf()).ifPresent(existsCpfUser -> {
            if(!existsCpfUser.getId().equals(user.getId())) {
                throw new IllegalArgumentException("Este usuário já pertence a esse cpf");
            }
        });

        foundUser.updateEntity(user);

        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> findByUser(String name, String email) {
        return userRepository.findByUser(name, email);
    }

    @Transactional
    public Optional<User> findByCpfAndActiveUser(String cpf, boolean active) {
        return userRepository.findByCpfAndActiveUser(cpf, active);
    }

    public List<User> findAllBeforeCreatedAt() {
        return userRepository.findAllBeforeCreatedAt();
    }

    public List<User> findAllOldBirthdate() {
        return userRepository.findAllOldBirthdate();
    }

    @Transactional
    public void delete(Long id) {

        userRepository.findById(id).ifPresent(user -> {
           if(!userRepository.existsById(id)) {
               throw new IllegalArgumentException("Este usuário não existe para ser deletado");
           }
        });

        userRepository.deleteById(id);
    }
}
