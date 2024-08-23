package com.recrutaibackend.profile.employment;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_employment_type")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class EmploymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Short id;

    @Column(name = "name")
    private String name;
}
