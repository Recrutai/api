package com.recrutaibackend.institution.school;

import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.institution.InstitutionSize;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id")
    @MapsId
    private Institution institution;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_size_id")
    private InstitutionSize schoolSize;

    @Column(name = "associated_alumni")
    private Integer associatedAlumni;

    public School(Institution institution, InstitutionSize schoolSize) {
        this.institution = institution;
        this.schoolSize = schoolSize;
    }
}
