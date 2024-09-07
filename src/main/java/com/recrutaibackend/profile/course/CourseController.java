package com.recrutaibackend.profile.course;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
    private final CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/users/{user_id}/courses")
    ResponseEntity<CourseResponse> create(@PathVariable("user_id") long id, @RequestBody @Valid CourseRequest request) {
        var course = courseService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("/users/{user_id}/courses")
    ResponseEntity<List<CourseResponse>> findAllByUserId(@PathVariable("user_id") long id) {
        var courses = courseService.findAllByUsersId(id);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/courses/{course_id}")
    ResponseEntity<Void> delete(@PathVariable("course_id") long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
