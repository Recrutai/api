package com.recrutaibackend.repository;

import com.recrutaibackend.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
}
