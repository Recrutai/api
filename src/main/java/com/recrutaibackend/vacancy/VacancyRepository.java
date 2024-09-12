package com.recrutaibackend.vacancy;

import com.recrutaibackend.shared.EmploymentType;
import com.recrutaibackend.shared.WorkModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @EntityGraph(attributePaths = {"location", "publishedBy.institution.headquarters"})
    List<Vacancy> findAllByPublishedBy_Institution_Id(long institutionId);

    @Query("""
            SELECT new com.recrutaibackend.vacancy.VacancySummaryResponse(
                v.id,
                v.title,
                v.workModel,
                v.publishedAt,
                i.id,
                i.name,
                concat(a.city, ', ', a.state)
            )
            FROM Vacancy v
            JOIN FETCH Address a ON v.location.id = a.id
            JOIN FETCH Institution i ON v.publishedBy.institution.id = i.id
            WHERE (:title = '' OR (lower(v.title) LIKE concat('%', lower(:title), '%')))
            AND (:locationId IS NULL OR v.location.id = :locationId)
            AND (:workModel IS NULL OR v.workModel = :workModel)
            AND (:employmentType IS NULL OR v.employmentType = :employmentType)""")
    List<VacancySummaryResponse> findAllFiltered(
            @Param("title") String title,
            @Param("locationId") Long locationId,
            @Param("workModel") WorkModel workModel,
            @Param("employmentType") EmploymentType employmentType
    );

}
