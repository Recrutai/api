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
@Table(name = "tbl_vacations")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    //Provalmente mudar para uma ENUM
    private String role;
    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL
    )
    private List<User> candidates = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Recruiter recruiter;
}
