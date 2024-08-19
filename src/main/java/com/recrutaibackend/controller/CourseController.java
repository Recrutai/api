package com.recrutaibackend.controller;

import com.recrutaibackend.dto.CourseRequest;
import com.recrutaibackend.dto.CourseResponse;
import com.recrutaibackend.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<CourseResponse>> findAllByUserId(@PathVariable @NotNull int userId) {
        var courses = courseService.findAllByUsersId(userId);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable @NotNull int id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
