package com.recrutaibackend.institution;

import com.recrutaibackend.address.Address;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.industry.Industry;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_institution")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private InstitutionType type;

    @Column(name = "headline")
    private String headline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "founder_id")
    private User founder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Column(name = "company_size", columnDefinition = "bpchar(2)")
    @Enumerated(EnumType.STRING)
    private InstitutionSize companySize;

    @Column(name = "associated_employees")
    private Integer associatedEmployees;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "headquarters_id")
    private Address headquarters;

    @Column(name = "website")
    private String website;

    @Column(name = "about")
    private String about;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "deactivated_at")
    private Instant deactivatedAt;

    public Institution(
            String name,
            InstitutionType type,
            String headline,
            User founder,
            Industry industry,
            InstitutionSize companySize,
            Address headquarters,
            String website,
            String about
    ) {
        this.name = name;
        this.type = type;
        this.headline = headline;
        this.founder = founder;
        this.industry = industry;
        this.companySize = companySize;
        this.associatedEmployees = 0;
        this.headquarters = headquarters;
        this.website = website;
        this.about = about;
    }

}
