package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.CourseRequest;
import com.recrutaibackend.dto.CourseResponse;
import com.recrutaibackend.model.Course;
import com.recrutaibackend.model.User;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

    public Course mapToEntity(CourseRequest request, User user) {
        return new Course(
                request.name(),
                request.description(),
                request.sender(),
                request.conclusion(),
                user
        );
    }

    public CourseResponse mapToResponse(Course course) {
        return new CourseResponse(
                course.getName(),
                course.getDescription(),
                course.getSender(),
                course.getConclusion()
        );
    }
}
