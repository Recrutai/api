package com.recrutai.api.vacancy;

import com.recrutai.api.address.Address;
import com.recrutai.api.institution.Institution;
import com.recrutai.api.institution.member.Member;
import com.recrutai.api.shared.CurrencyCode;
import com.recrutai.api.shared.EmploymentType;
import com.recrutai.api.shared.WorkModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_vacancy")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "employment_type")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Column(name = "work_model")
    @Enumerated(EnumType.STRING)
    private WorkModel workModel;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "location_id")
    private Address location;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "currency_code", columnDefinition = "bpchar(3)")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "positions")
    private Short positions;

    @Column(name = "applications")
    private Integer applications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruiter_id")
    private Member recruiter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "published_by_id")
    private Member publishedBy;

    @CreationTimestamp
    @Column(name = "published_at")
    private Instant publishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closed_by_id")
    private Member closedBy;

    @Column(name = "closed_at")
    private Instant closedAt;

    public Vacancy(
            String title,
            String description,
            EmploymentType employmentType,
            WorkModel workModel,
            Address location,
            Integer salary,
            CurrencyCode currencyCode,
            Short positions,
            Institution institution,
            Member recruiter,
            Member publishedBy
    ) {
        this.title = title;
        this.description = description;
        this.employmentType = employmentType;
        this.workModel = workModel;
        this.location = location;
        this.salary = salary;
        this.currencyCode = currencyCode;
        this.positions = positions;
        this.applications = 0;
        this.institution = institution;
        this.recruiter = recruiter;
        this.publishedBy = publishedBy;
    }

}
