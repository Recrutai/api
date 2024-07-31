package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_application")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id")
    private User candidate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @Column(name = "expected_salary")
    private Integer expectedSalary;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @CreationTimestamp
    @Column(name = "applied_at")
    private Instant appliedAt;

    public Application(User candidate, Vacancy vacancy, Integer expectedSalary) {
        this.candidate = candidate;
        this.vacancy = vacancy;
        this.expectedSalary = expectedSalary;
        this.status = ApplicationStatus.RECEIVED;
    }
}
