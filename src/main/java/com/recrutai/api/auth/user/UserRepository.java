package com.recrutai.api.auth.user;

import com.recrutai.api.vacancy.VacancyResponse;
import com.recrutai.api.vacancy.application.ApplicationResponse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"location"})
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
