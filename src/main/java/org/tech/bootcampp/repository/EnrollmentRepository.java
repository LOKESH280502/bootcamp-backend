package org.tech.bootcampp.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tech.bootcampp.dao.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

//    boolean existsByUserEmailAndCourseId(String userEmail, Long courseId);

    List<Enrollment> findByUserEmail(String userEmail);
    boolean existsByUserEmailAndCourseId(String userEmail, Long courseId);

    @Query("SELECT e.courseId FROM Enrollment e WHERE e.userEmail = :email")
    List<Long> findCourseIdsByUserEmail(@Param("email") String email);


}
