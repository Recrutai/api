package com.recrutaibackend.profile.course;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    ResponseEntity<CourseResponse> create(@RequestBody @Valid CourseRequest request) {
        var course = courseService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<CourseResponse>> findAllByUserId(@PathVariable long userId) {
        var courses = courseService.findAllByUsersId(userId);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
