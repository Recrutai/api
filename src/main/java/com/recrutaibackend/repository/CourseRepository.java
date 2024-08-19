package com.recrutaibackend.repository;

import com.recrutaibackend.model.Course;
import com.recrutaibackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByUser(User user);

}
