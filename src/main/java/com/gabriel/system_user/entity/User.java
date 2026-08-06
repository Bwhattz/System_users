package com.gabriel.system_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private boolean active;

    public User(Long id, String name, String email, String password, String cpf, LocalDate birthdate, boolean active) {
     this.id = id;
     this.name = name;
     this.email = email;
     this.password = password;
     this.cpf = cpf;
     this.birthdate = birthdate;
     this.active = active;
    }

    public void block() {
        this.active = false;
    }

    public void active() {
        this.active = true;
    }

    public int getAge() {
        if(this.birthdate == null) return 0;

        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }
}
