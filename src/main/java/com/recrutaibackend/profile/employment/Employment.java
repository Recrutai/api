package com.recrutaibackend.profile.employment;

import com.recrutaibackend.address.Address;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.shared.EmploymentType;
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
        this.title = title;
        this.type = type;
        this.workModel = workModel;
        this.address = address;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
