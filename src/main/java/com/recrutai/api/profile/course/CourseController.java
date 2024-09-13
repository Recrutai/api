package com.recrutai.api.profile.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Courses")
public class CourseController {
    private final CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Add a new course to user's profile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    })
    @PostMapping("/users/{user_id}/courses")
    ResponseEntity<CourseResponse> create(@PathVariable("user_id") long id, @RequestBody @Valid CourseRequest request) {
        var course = courseService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @Operation(summary = "Find all user's courses")
    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)
    @GetMapping("/users/{user_id}/courses")
    ResponseEntity<List<CourseResponse>> findAllByUserId(@PathVariable("user_id") long id) {
        var courses = courseService.findAllByUsersId(id);
        return ResponseEntity.ok(courses);
    }

    @Operation(summary = "Delete course by its id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @DeleteMapping("/courses/{course_id}")
    ResponseEntity<Void> delete(@PathVariable("course_id") long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
