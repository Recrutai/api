package com.recrutai.api.profile.course;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @EntityGraph(attributePaths = {"school.organization", "school.organization.headquarters"})
    List<Course> findAllByUserId(long userId);

}
