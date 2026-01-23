package org.tech.bootcampp.controller.copy;
 
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.tech.bootcampp.dto.CourseRequest;
import org.tech.bootcampp.service.CourseService;

@RestController
@RequestMapping("/api/v1/bootcamps/{bootcampId}/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ================= CREATE COURSE =================
    @PostMapping
    @PreAuthorize("hasAuthority('publisher')")
    public ResponseEntity<?> createCourse(
            @PathVariable Long bootcampId,
            @RequestBody CourseRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "success", true,
                        "data", courseService.createCourse(bootcampId, request)
                )
        );
    }

    // ================= GET COURSES =================
    @GetMapping
    public ResponseEntity<?> getCourses(
            @PathVariable Long bootcampId) {

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "data", courseService.getCoursesByBootcamp(bootcampId)
                )
        );
    }

    // ================= UPDATE COURSE =================
    @PutMapping("/{courseId}")
    @PreAuthorize("hasAuthority('publisher')")
    public ResponseEntity<?> updateCourse(
            @PathVariable Long courseId,
            @RequestBody CourseRequest request) {

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "data", courseService.updateCourse(courseId, request)
                )
        );
    }

    // ================= DELETE COURSE =================
    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAuthority('publisher')")
    public ResponseEntity<?> deleteCourse(
            @PathVariable Long courseId) {

        courseService.deleteCourse(courseId);

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Course deleted successfully"
                )
        );
    }
}
