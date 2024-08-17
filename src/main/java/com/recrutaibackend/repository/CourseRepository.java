package com.recrutaibackend.repository;

import com.recrutaibackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
