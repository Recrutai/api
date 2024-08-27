package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.Address;
import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.shared.WorkModel;
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
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id")
    private EmploymentType type;

    @Column(name = "work_model")
    @Enumerated(EnumType.STRING)
    private WorkModel workModel;

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
            EmploymentType type,
            WorkModel workModel,
            String description,
            Integer startDate,
            Integer endDate
    ) {
        this.user = user;
        this.institution = institution;
        this.title = title;
        this.type = type;
        this.workModel = workModel;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
