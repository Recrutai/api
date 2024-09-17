package com.recrutai.api.organization;

import com.recrutai.api.address.Address;
import com.recrutai.api.auth.user.User;
import com.recrutai.api.industry.Industry;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_organization")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OrganizationType type;

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
    private OrganizationSize companySize;

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

    public Organization(
            String name,
            OrganizationType type,
            String headline,
            User founder,
            Industry industry,
            OrganizationSize companySize,
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
