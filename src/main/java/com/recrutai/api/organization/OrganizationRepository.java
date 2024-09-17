package com.recrutai.api.organization;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @EntityGraph(attributePaths = {"industry", "headquarters"})
    Optional<Organization> findWithIndustryAndHeadquartersById(long id);

    @Query("""
            SELECT new com.recrutai.api.organization.OrganizationSummaryResponse(
                inst.id,
                inst.name,
                inst.headline,
                ind.name,
                CASE WHEN a IS NOT NULL THEN concat(a.city, ', ', a.state) ELSE NULL END
            )
            FROM Organization inst
            JOIN Industry ind ON inst.industry.id = ind.id
            LEFT JOIN Address a ON inst.headquarters.id = a.id
            WHERE (:name = '' OR (lower(inst.name) LIKE concat('%', lower(:name), '%')))
            """)
    List<OrganizationSummaryResponse> search(@Param("name") String name);

}
