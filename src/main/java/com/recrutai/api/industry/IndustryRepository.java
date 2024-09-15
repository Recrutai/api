package com.recrutai.api.industry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Short> {

    Optional<Industry> findByNameIgnoreCase(String name);

    @Query("""
            SELECT i.name
            FROM Industry i
            WHERE (:name = '' OR (lower(i.name) LIKE concat('%', lower(:name), '%')))
            """)
    List<String> findAllFiltered(@Param("name") String name);

}
