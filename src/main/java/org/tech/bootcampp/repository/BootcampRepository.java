package org.tech.bootcampp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tech.bootcampp.dao.Bootcamp;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {
}

