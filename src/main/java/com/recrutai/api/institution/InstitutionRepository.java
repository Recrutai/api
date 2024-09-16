package com.recrutai.api.institution;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @EntityGraph(attributePaths = {"industry", "headquarters"})
    Optional<Institution> findWithIndustryAndHeadquartersById(long id);

    @Query("""
            SELECT new com.recrutai.api.institution.InstitutionSummaryResponse(
                inst.id,
                inst.name,
                inst.headline,
                ind.name,
                concat(a.city, ', ', a.state)
            )
            FROM Institution inst
            JOIN Address a ON inst.headquarters.id = a.id
            JOIN Industry ind ON inst.industry.id = ind.id
            WHERE (:name = '' OR (lower(inst.name) LIKE concat('%', lower(:name), '%')))
            """)
    List<InstitutionSummaryResponse> search(@Param("name") String name);

}
