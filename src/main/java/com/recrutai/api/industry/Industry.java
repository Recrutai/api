package com.recrutai.api.industry;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_industry")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Short id;

    @Column(name = "name")
    private String name;
}
