package com.company.localApp.users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    private Long id;
    private String name;
    //TODO add image
    private String description;
    private String password;
    //TODO add role
}