package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
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
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruiter_id")
    private Member recruiter;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "work_model")
    private String workModel;

    @Column(name = "avg_salary")
    private Integer avgSalary;

    @Column(name = "positions")
    private Short positions;

    @Column(name = "applications")
    private Integer applications;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "published_by")
    private Member publishedBy;

    @CreationTimestamp
    @Column(name = "published_at")
    private Instant publishedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "closed_by")
    private Member closedBy;

    @Column(name = "closed_at")
    private Instant closedAt;

    public Vacancy(
            Member recruiter,
            String title,
            String description,
            String workModel,
            Integer avgSalary,
            Short positions,
            Member publishedBy
    ) {
        this.recruiter = recruiter;
        this.title = title;
        this.description = description;
        this.workModel = workModel;
        this.avgSalary = avgSalary;
        this.positions = positions;
        this.publishedBy = publishedBy;
    }
}
