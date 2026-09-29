package com.gabriel.system_user.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false, length = 8)
    private String idUser;

    @Column(name = "user_name", nullable = false, length = 120)
    private String name;

    @Column(name = "user_last_name", nullable = false, length = 120)
    private String lastName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false, length = 120)
    private String password;

    @Column(name = "user_cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "user_telephone", nullable = false, unique = true, length = 20)
    private String telephone;

    @Column(name = "user_birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "user_age", nullable = false)
    private int age;

    @Column(name = "user_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_status", nullable = false, columnDefinition = "TEXT")
    private String status;

    @Column(name = "user_active", nullable = false)
    private boolean isActive = true;

    @PrePersist
    protected void validates() {

        if(idUser == null || idUser.isBlank()) {
            this.idUser = UUID.randomUUID().toString()
                    .replace("-", "")
                    .substring(0, 8)
                    .toUpperCase();
        }

        if(this.birthdate != null) {
            this.age = Period.between(birthdate, LocalDate.now()).getYears();
        } else {
            this.age = 0;
        }

        if(this.status == null || this.status.isBlank()) {
            this.status = "ACTIVE";
        } else {
            this.status = this.status.trim().toUpperCase();
        }

        if(this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public void updateEntity(User userUpdate) {

        if(this.getName() != null) {
            this.name = userUpdate.getName();
        }

        if(this.getLastName() != null) {
            this.lastName = userUpdate.getLastName();
        }

        if(this.getEmail() != null) {
            this.email = userUpdate.getEmail();
        }

        if(this.password != null) {
            this.password = userUpdate.getPassword();
        }

        if(this.cpf != null) {
            this.cpf = userUpdate.getCpf();
        }

        if(this.telephone != null) {
            this.telephone = userUpdate.getTelephone();
        }

        if(this.birthdate != null) {
            this.birthdate = userUpdate.getBirthdate();
        }

    }
}
