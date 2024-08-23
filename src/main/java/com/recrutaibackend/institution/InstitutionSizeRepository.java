package com.recrutaibackend.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionSizeRepository extends JpaRepository<InstitutionSize, Short> {
}
