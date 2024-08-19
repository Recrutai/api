package com.recrutaibackend.repository;

import com.recrutaibackend.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Short> {

    Optional<Industry> findByName(String name);

}
