package com.recrutaibackend.repository;

import com.recrutaibackend.model.Job;
import com.recrutaibackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findAllByUser(User user);
}
