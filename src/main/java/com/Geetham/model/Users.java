package com.Geetham.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Email")
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="Username")
    private String username;
    @Column(name="Role")
    private String role;


}
