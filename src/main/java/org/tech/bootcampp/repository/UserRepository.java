package org.tech.bootcampp.repository;

 

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tech.bootcampp.dao.User;
 

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
