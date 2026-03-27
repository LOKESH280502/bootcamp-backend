package org.tech.bootcampp.service;

 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.tech.bootcampp.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Long> getMyCourses(String email) {
        return enrollmentRepository.findByUserEmail(email)
                .stream()
                .map(e -> e.getCourseId())
                .collect(Collectors.toList());
    }
}

