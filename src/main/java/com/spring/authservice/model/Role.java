package com.spring.authservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Role")
public class Role {

    @Id
    int id;

    @Column(name = "role_name")
    String roleName;
}
