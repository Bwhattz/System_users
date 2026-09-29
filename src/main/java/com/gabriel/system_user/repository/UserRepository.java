package com.gabriel.system_user.repository;

import com.gabriel.system_user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT user.* FROM users AS user" +
                    "WHERE user.user_name = :name AND user.user_email = :email",
            nativeQuery = true
    )
    Optional<User> findByUser(@Param("name") String name, @Param("email") String email);

    Optional<User> findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);

    @Query(
            value = "SELECT user.* FROM users AS user" +
                    "WHERE user.user_cpf = :cpf AND user.user_active = :active",
            nativeQuery = true
    )
    Optional<User> findByCpfAndActiveUser(@Param("cpf") String cpf, @Param("active") boolean active);

    Optional<User> findByCpf(@Param("cpf") String cpf);

    boolean existsByCpf(String cpf);

    @Query(
            value = "SELECT user.* FROM users AS user" +
                    "WHERE user.user_created_at < CURRENT_DATE",
            nativeQuery = true
    )
    List<User> findAllBeforeCreatedAt();

    @Query(
            value = "SELECT user.* FROM users AS user" +
                    "WHERE user.user_birthdate < CURRENT_DATE",
            nativeQuery = true
    )
    List<User> findAllOldBirthdate();
}
