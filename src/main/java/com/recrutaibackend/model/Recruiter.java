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
@Table(name = "tbl_recruiters")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String company;
    private String password;
    @OneToMany(
            mappedBy = "vacation",
            cascade = CascadeType.ALL
    )
    private List<Vacation> vacations = new ArrayList<>();

}
