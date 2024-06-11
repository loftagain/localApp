package com.company.localApp.users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@Data
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;

    private String description;
    private String password;
    private String email;
    private String imagePath;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(Long id, String name, String description, String password, String email) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.password = password;
        this.email = email;
    }
}