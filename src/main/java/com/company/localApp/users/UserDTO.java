package com.company.localApp.users;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//made to map only needed variables to client, protecting passwords etc.
public record UserDTO(

                String name,

                String description

) {

}
