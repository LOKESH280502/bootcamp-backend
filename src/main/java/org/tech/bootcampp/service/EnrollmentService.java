package org.tech.bootcampp.service;

 
import java.util.List;

public interface EnrollmentService {
    List<Long> getMyCourses(String email);
}

