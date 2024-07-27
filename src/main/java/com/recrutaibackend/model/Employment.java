package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_employment")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Integer startDate;

    @Column(name = "end_date")
    private Integer endDate;

    public Employment(
            User user,
            Institution institution,
            String title,
            String type,
            String description,
            Integer startDate,
            Integer endDate
    ) {
        this.user = user;
        this.institution = institution;
        this.title = title;
        this.type = type;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
