package com.recrutai.api.profile.employment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {

    @EntityGraph(attributePaths = {"address", "organization", "organization.headquarters"})
    List<Employment> findAllWithAddressByUserId(long userId);

    List<Employment> findAllByUserId(long id);

}
