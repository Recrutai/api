package com.recrutaibackend.vacancy;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @EntityGraph(attributePaths = {"location", "publishedBy.institution.headquarters"})
    List<Vacancy> findAllWithLocationByTitleContainsIgnoreCase(String title);

    @EntityGraph(attributePaths = {"location", "publishedBy.institution.headquarters"})
    List<Vacancy> findAllWithLocationBy();

    @EntityGraph(attributePaths = {"location", "publishedBy.institution.headquarters"})
    List<Vacancy> findAllByPublishedBy_Institution_Id(long institutionId);

}
