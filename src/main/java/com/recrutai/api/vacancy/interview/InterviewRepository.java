package com.recrutai.api.vacancy.interview;

import com.recrutai.api.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> getAllByApplicationCandidate(User candidate);
}
