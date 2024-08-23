package com.recrutaibackend.vacancy.application;

import com.recrutaibackend.shared.CurrencyCode;
import com.recrutaibackend.auth.user.User;
import com.recrutaibackend.vacancy.Vacancy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

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
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id")
    private User candidate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @Column(name = "expected_salary")
    private Integer expectedSalary;

    @Column(name = "currency_code", columnDefinition = "bpchar(3)")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @CreationTimestamp
    @Column(name = "applied_at")
    private Instant appliedAt;

    public Application(User candidate, Vacancy vacancy, Integer expectedSalary, CurrencyCode currencyCode) {
        this.candidate = candidate;
        this.vacancy = vacancy;
        this.expectedSalary = expectedSalary;
        this.currencyCode = currencyCode;
    }
}