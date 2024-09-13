package com.recrutai.api.institution;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @EntityGraph(attributePaths = {"industry", "headquarters"})
    List<Institution> findAllWithIndustryAndHeadquartersBy();

    @EntityGraph(attributePaths = {"industry", "headquarters"})
    Optional<Institution> findWithIndustryAndHeadquartersById(long id);

}
