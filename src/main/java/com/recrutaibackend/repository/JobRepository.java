package com.recrutaibackend.repository;

import com.recrutaibackend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
