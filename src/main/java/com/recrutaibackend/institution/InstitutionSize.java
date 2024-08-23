package com.recrutaibackend.institution;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_institution_size")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class InstitutionSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Short id;

    @Column(name = "lower_bound")
    private Short lowerBound;

    @Column(name = "upper_bound")
    private Short upperBound;
}
