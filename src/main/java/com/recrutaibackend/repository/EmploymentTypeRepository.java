package com.recrutaibackend.repository;

import com.recrutaibackend.model.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Integer> {

    Optional<EmploymentType> findByName(String name);

}
