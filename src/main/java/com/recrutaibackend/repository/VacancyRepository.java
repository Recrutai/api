package com.recrutaibackend.repository;

import com.recrutaibackend.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    Optional<Vacancy> findByTitle(String title);

    List<Vacancy> findAllByTitleContains(String title);
}
