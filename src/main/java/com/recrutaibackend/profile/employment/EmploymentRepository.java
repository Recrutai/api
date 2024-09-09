package com.recrutaibackend.profile.employment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {

    @EntityGraph(attributePaths = {"address", "institution", "institution.headquarters"})
    List<Employment> findAllWithAddressByUserId(long userId);

}
