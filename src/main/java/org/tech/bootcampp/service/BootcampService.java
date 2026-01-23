package org.tech.bootcampp.service;

import java.util.List;

import org.tech.bootcampp.dao.Bootcamp;
import org.tech.bootcampp.dto.CreateBootcampRequest;
import org.tech.bootcampp.dto.UpdateBootcampRequest;

public interface BootcampService {

    Bootcamp create(CreateBootcampRequest request);

    List<Bootcamp> getAll();

    Bootcamp getById(Long id);

    Bootcamp update(Long id, UpdateBootcampRequest request);

    void delete(Long id);
}
