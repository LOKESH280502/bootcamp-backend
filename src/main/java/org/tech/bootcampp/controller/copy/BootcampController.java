package org.tech.bootcampp.controller.copy;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tech.bootcampp.dto.CreateBootcampRequest;
import org.tech.bootcampp.dto.UpdateBootcampRequest;
import org.tech.bootcampp.service.BootcampService;

@RestController
@RequestMapping("/api/v1/bootcamps")
public class BootcampController {

    private final BootcampService service;

    public BootcampController(BootcampService service) {
        this.service = service;
    }
    

    // CREATE (publisher)
    @PostMapping
    @PreAuthorize("hasRole('publisher')")
    public Map<String, Object> create(@RequestBody CreateBootcampRequest r) {
        return Map.of("success", true, "data", service.create(r));
    }

    // GET ALL (public)
    @GetMapping
    public Map<String, Object> getAll() {
        var list = service.getAll();
        return Map.of("success", true, "count", list.size(), "data", list);
    }

    // GET ONE (public)
    @GetMapping("/{id}")
    public Map<String, Object> getOne(@PathVariable Long id) {
        return Map.of("success", true, "data", service.getById(id));
    }

    // UPDATE (publisher)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PUBLISHER')")
    public Map<String, Object> update(
            @PathVariable Long id,
            @RequestBody UpdateBootcampRequest r) {
        return Map.of("success", true, "data", service.update(id, r));
    }


    // DELETE (publisher)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('publisher')")
    public Map<String, Object> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("success", true, "message", "Bootcamp deleted");
    }
}

