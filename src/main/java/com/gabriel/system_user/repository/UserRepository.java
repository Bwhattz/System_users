package com.gabriel.system_user.repository;

import com.gabriel.system_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM tb_users WHERE cpf = :cpf AND email = :email AND password = :password",
            nativeQuery = true
    )

    Optional<User> findByCpfAndEmailAndPassword(
            @Param("cpf") String cpf,
            @Param("email") String email,
            @Param("password") String password
    );
}
