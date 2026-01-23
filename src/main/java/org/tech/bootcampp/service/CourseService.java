package org.tech.bootcampp.service;

 
import java.util.List;

import org.springframework.stereotype.Service;
import org.tech.bootcampp.dao.Bootcamp;
import org.tech.bootcampp.dao.Course;
import org.tech.bootcampp.dto.CourseRequest;
 
import org.tech.bootcampp.repository.BootcampRepository;
import org.tech.bootcampp.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final BootcampRepository bootcampRepository;

    public CourseService(CourseRepository courseRepository,
                         BootcampRepository bootcampRepository) {
        this.courseRepository = courseRepository;
        this.bootcampRepository = bootcampRepository;
    }

    // CREATE
    public Course createCourse(Long bootcampId, CourseRequest request) {

        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(() -> new RuntimeException("Bootcamp not found"));

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setPrice(request.getPrice());
        course.setMinimumSkill(request.getMinimumSkill());
        course.setScholarshipAvailable(request.isScholarshipAvailable());
        course.setImage(request.getImage());
        course.setBootcamp(bootcamp);

        return courseRepository.save(course);
    }

    // GET ALL COURSES BY BOOTCAMP
    public List<Course> getCoursesByBootcamp(Long bootcampId) {
        return courseRepository.findByBootcampId(bootcampId);
    }

    // UPDATE
    public Course updateCourse(Long courseId, CourseRequest request) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setPrice(request.getPrice());
        course.setMinimumSkill(request.getMinimumSkill());
        course.setScholarshipAvailable(request.isScholarshipAvailable());
        course.setImage(request.getImage());

        return courseRepository.save(course);
    }

    // DELETE
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
