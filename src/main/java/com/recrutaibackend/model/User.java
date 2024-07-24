package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String password;
    //Provalmente mudar para uma ENUM
    private String role;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Interview> interviews = new ArrayList<>();
    @OneToMany(
            mappedBy = "vacation",
            cascade = CascadeType.ALL
    )
    private List<Vacation> vacations = new ArrayList<>();


}
