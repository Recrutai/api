package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tbl_applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Vacancy vacancy;
    private Double expectedSalary;
    private String status;
    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL
    )
    private Set<Interview> interviews = new HashSet<>();
    private Timestamp appliedAt;
}
