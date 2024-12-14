package com.spring.authservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_login_info")
public class UserLoginInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "modified_on")
    private LocalDate modifiedOn;
}
