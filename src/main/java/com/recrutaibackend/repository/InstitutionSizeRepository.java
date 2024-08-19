package com.recrutaibackend.repository;

import com.recrutaibackend.model.InstitutionSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionSizeRepository extends JpaRepository<InstitutionSize, Short> {
}
