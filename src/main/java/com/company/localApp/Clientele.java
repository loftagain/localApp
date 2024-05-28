package com.company.localApp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@Data //getters, setters, toString, hashCode, equals.
@Entity //this is a class mapped to a database table!
@Table(name="clientele")
public class Clientele {
@Id
@GeneratedValue(strategy = GenerationType.TABLE) //uses underlying table, ensures uniqueness
    @Column(name="id")
//TODO these two need additional code in controller
//@NotNull(message="Name is mandatory")
//@NotBlank(message="Name is mandatory")
    private int id;
@Column(name="nickname")
    private String nickname;
@Column(name="phone_number")
    private String phone_number;
@Column(name="bank_account")
    private String bank_account;
//a regular constructor for evrth but ID
    public Clientele(String bank_account, String phone_number, String nickname) {
        this.bank_account = bank_account;
        this.phone_number = phone_number;
        this.nickname = nickname;
    }
}
