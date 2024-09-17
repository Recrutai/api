package com.recrutai.api.profile.course;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.organization.school.School;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_course")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "fallback_school_name")
    private String fallbackSchoolName;

    @Column(name = "name")
    private String name;

    @Column(name = "workload_hours")
    private Integer workloadHours;

    @Column(name = "completion_date")
    private Integer completionDate;

    @Column(name = "description")
    private String description;

    public Course(
            User user,
            School school,
            String fallbackSchoolName,
            String name,
            Integer workloadHours,
            Integer completionDate,
            String description
    ) {
        this.user = user;
        this.school = school;
        this.fallbackSchoolName = fallbackSchoolName;
        this.name = name;
        this.workloadHours = workloadHours;
        this.completionDate = completionDate;
        this.description = description;
    }

}
