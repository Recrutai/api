package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.CourseRequest;
import com.recrutaibackend.dto.CourseResponse;
import com.recrutaibackend.model.Course;
import com.recrutaibackend.model.School;
import com.recrutaibackend.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CourseMapper {

    private final UserMapper userMapper;
    private final SchoolMapper schoolMapper;

    public CourseMapper(UserMapper userMapper, SchoolMapper schoolMapper) {
        this.userMapper = userMapper;
        this.schoolMapper = schoolMapper;
    }

    public Course mapToEntity(CourseRequest request, User user, School school) {
        return new Course(
                user,
                school,
                request.name(),
                request.workloadHours(),
                this.convertDateToYYYYMM(request.completionDate()),
                request.description()
        );
    }

    public CourseResponse mapToResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                userMapper.mapToResponse(course.getUser()),
                schoolMapper.mapToResponse(course.getSchool()),
                course.getName(),
                course.getWorkloadHours(),
                this.convertYYYYMMToDate(course.getCompletionDate()),
                course.getDescription()
        );
    }

    private int convertDateToYYYYMM(LocalDate date) {
        return date.getYear() * 100 + date.getMonthValue();
    }

    private LocalDate convertYYYYMMToDate(Integer yearAndMonth) {
        return LocalDate.of(yearAndMonth / 100, yearAndMonth % 100, 1);
    }
}
