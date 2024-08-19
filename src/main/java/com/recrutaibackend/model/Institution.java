package com.recrutaibackend.model;

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
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_size_id")
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
            User owner,
            Industry industry,
            InstitutionSize companySize,
            Address headquarters,
            String website,
            String about
    ) {
        this.name = name;
        this.type = type;
        this.headline = headline;
        this.owner = owner;
        this.industry = industry;
        this.companySize = companySize;
        this.headquarters = headquarters;
        this.website = website;
        this.about = about;
    }
}
