package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

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
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private InstitutionType type;

    @Column(name = "industry")
    private String industry;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "employees")
    @Generated(event = EventType.INSERT)
    private Integer employees;

    @Column(name = "alumni")
    @Generated(event = EventType.INSERT)
    private Integer alumni;

    @Column(name = "description")
    private String description;

    @Column(name = "website")
    private String website;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    private Institution(
            User owner,
            String name,
            InstitutionType type,
            String industry,
            Address address,
            String description,
            String website
    ) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.industry = industry;
        this.address = address;
        this.description = description;
        this.website = website;
    }

    public static Institution createCompany(
            User owner,
            String name,
            String industry,
            Address address,
            String description,
            String website
    ) {
        return new Institution(
                owner,
                name,
                InstitutionType.COMPANY,
                industry,
                address,
                description,
                website
        );
    }

    public static Institution createEducationalInstitution(
            User owner,
            String name,
            Address address,
            String description,
            String website
    ) {
        return new Institution(
                owner,
                name,
                InstitutionType.EDUCATIONAL_INSTITUTION,
                null,
                address,
                description,
                website
        );
    }
}
