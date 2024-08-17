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

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @Transactional
    ResponseEntity<CourseResponse> create(@RequestBody @Valid CourseRequest request) {
        var course = courseService.create(request);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<CourseResponse>> getAll(@PathVariable @NotNull int id) {
        var courses = courseService.getAll(id);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable @NotNull int id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
