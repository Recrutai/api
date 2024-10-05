package com.recrutai.api.vacancy;

import com.recrutai.api.shared.EmploymentType;
import com.recrutai.api.shared.WorkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("""
            SELECT new com.recrutai.api.vacancy.VacancySummaryResponse(
                v.id,
                v.title,
                v.employmentType,
                v.workModel,
                v.salary,
                v.currencyCode,
                v.publishedAt,
                i.id,
                i.name,
                concat(a.city, ', ', a.state)
            )
            FROM Vacancy v
            JOIN FETCH Address a ON v.location.id = a.id
            JOIN FETCH Organization i ON v.organization.id = i.id
            WHERE (:title = '' OR (lower(v.title) LIKE concat('%', lower(:title), '%')))
            AND (:locationId IS NULL OR a.id = :locationId)
            AND (:organizationId IS NULL OR i.id = :organizationId)
            AND (:workModel IS NULL OR v.workModel = :workModel)
            AND (:employmentType IS NULL OR v.employmentType = :employmentType)
            """)
    List<VacancySummaryResponse> search(
            @Param("title") String title,
            @Param("locationId") Long locationId,
            @Param("organizationId") Long organizationId,
            @Param("workModel") WorkModel workModel,
            @Param("employmentType") EmploymentType employmentType
    );
}
