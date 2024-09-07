package com.recrutaibackend.vacancy.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByCandidateId(long id);

    List<Application> findAllByVacancyId(long id);

}
