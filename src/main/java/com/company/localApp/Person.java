package com.company.localApp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    private Long id;
    private String name;
    //TODO add image
    private String description;
}