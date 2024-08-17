package com.recrutaibackend.service;

import com.recrutaibackend.dto.CourseRequest;
import com.recrutaibackend.dto.CourseResponse;
import com.recrutaibackend.mappers.CourseMapper;
import com.recrutaibackend.repository.CourseRepository;
import com.recrutaibackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository,
                         UserRepository userRepository,
                         CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
    }

    public CourseResponse create(CourseRequest request) {
        var user = userRepository.findById(request.userId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
        var course = courseMapper.mapToEntity(request, user);
        courseRepository.save(course);
        return courseMapper.mapToResponse(course);
    }

    public List<CourseResponse> getAll(int id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
        var courses = courseRepository.findAllByUser(user);
        return courses.stream()
                .map(courseMapper::mapToResponse)
                .toList();
    }

    public void delete(int id) {
        var course = courseRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!")
        );
        courseRepository.delete(course);
    }
}
