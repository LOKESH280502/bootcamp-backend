package org.tech.bootcampp.controller.copy;

 
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tech.bootcampp.dao.Course;
import org.tech.bootcampp.dao.Enrollment;
import org.tech.bootcampp.repository.CourseRepository;
import org.tech.bootcampp.repository.EnrollmentRepository;

 @RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	 @Autowired
	 private EnrollmentRepository enrollmentRepository;
	 
     
    private final CourseRepository courseRepository;

    public EnrollmentController(
        EnrollmentRepository enrollmentRepository,
        CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/my-courses")
    public ResponseEntity<List<Course>> myCourses(Principal principal) {

        String email = principal.getName();

        List<Enrollment> enrollments =
            enrollmentRepository.findByUserEmail(email);

        List<Course> courses = enrollments.stream()
            .map(e -> courseRepository.findById(e.getCourseId()).orElse(null))
            .filter(Objects::nonNull)
            .toList();

        return ResponseEntity.ok(courses);
    }
    @GetMapping("/check/{courseId}")
    public ResponseEntity<Boolean> checkEnrollment(
            @PathVariable Long courseId,
            Principal principal
    ) {
        String userEmail = principal.getName();
        boolean exists = enrollmentRepository.existsByUserEmailAndCourseId(userEmail, courseId);
        return ResponseEntity.ok(exists);
    }

}


