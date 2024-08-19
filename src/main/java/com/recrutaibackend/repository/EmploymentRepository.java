package com.recrutaibackend.repository;

import com.recrutaibackend.model.Employment;
import com.recrutaibackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {

    List<Employment> findAllByUser(User user);

}
