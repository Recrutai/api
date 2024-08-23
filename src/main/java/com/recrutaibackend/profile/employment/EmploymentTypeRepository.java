package com.recrutaibackend.profile.employment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Integer> {

    Optional<EmploymentType> findByName(String name);

}
