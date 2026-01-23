package org.tech.bootcampp.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.tech.bootcampp.dao.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByBootcampId(Long bootcampId);
}

