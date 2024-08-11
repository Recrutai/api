package com.recrutaibackend.repository;

import com.recrutaibackend.model.Application;
import com.recrutaibackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {


    List<Application> findAllByCandidate(User user);
}
