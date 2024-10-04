package com.recrutai.api.organization.school;

import com.recrutai.api.organization.Organization;
import com.recrutai.api.organization.OrganizationSize;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_school")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class School {
    @Id
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    @MapsId
    private Organization organization;

    @Column(name = "school_size", columnDefinition = "bpchar(2)")
    @Enumerated(EnumType.STRING)
    private OrganizationSize schoolSize;

    @Column(name = "associated_alumni")
    private Integer associatedAlumni;

    public School(Organization organization, OrganizationSize schoolSize) {
        this.organization = organization;
        this.schoolSize = schoolSize;
        this.associatedAlumni = 0;
    }

}
