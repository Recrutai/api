package com.recrutai.api.profile.employment;

import com.recrutai.api.address.Address;
import com.recrutai.api.auth.user.User;
import com.recrutai.api.institution.Institution;
import com.recrutai.api.shared.EmploymentType;
import com.recrutai.api.shared.WorkModel;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Column(name = "fallback_institution_name")
    private String fallbackInstitutionName;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EmploymentType type;

    @Column(name = "work_model")
    @Enumerated(EnumType.STRING)
    private WorkModel workModel;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Integer startDate;

    @Column(name = "end_date")
    private Integer endDate;

    public Employment(
            User user,
            Institution institution,
            String fallbackInstitutionName,
            String title,
            EmploymentType type,
            WorkModel workModel,
            Address address,
            String description,
            Integer startDate,
            Integer endDate
    ) {
        this.user = user;
        this.institution = institution;
        this.fallbackInstitutionName = fallbackInstitutionName;
        this.title = title;
        this.type = type;
        this.workModel = workModel;
        this.address = address;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
