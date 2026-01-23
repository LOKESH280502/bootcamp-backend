package org.tech.bootcampp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tech.bootcampp.dao.Bootcamp;
import org.tech.bootcampp.dto.CreateBootcampRequest;
import org.tech.bootcampp.dto.UpdateBootcampRequest;
import org.tech.bootcampp.repository.BootcampRepository;

import jakarta.transaction.Transactional;

@Service
public class BootcampServiceImpl implements BootcampService {

    private final BootcampRepository repo;

    public BootcampServiceImpl(BootcampRepository repo) {
        this.repo = repo;
    }

    @Override
    public Bootcamp create(CreateBootcampRequest r) {
        Bootcamp b = new Bootcamp();
        b.setName(r.getName());
        b.setEmail(r.getEmail());
        b.setDescription(r.getDescription());
        b.setWebsite(r.getWebsite());
        b.setCareers(r.getCareers());
        b.setAddress(r.getAddress());
        b.setPhoto(r.getPhoto());
        return repo.save(b);
    }

    @Override
    public List<Bootcamp> getAll() {
        return repo.findAll();
    }

    @Override
    public Bootcamp getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bootcamp not found"));
    }

    @Transactional
    public Bootcamp update(Long id, UpdateBootcampRequest r) {

        Bootcamp b = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bootcamp not found"));

        if (r.getName() != null) b.setName(r.getName());
        if (r.getEmail() != null) b.setEmail(r.getEmail());
        if (r.getDescription() != null) b.setDescription(r.getDescription());
        if (r.getWebsite() != null) b.setWebsite(r.getWebsite());
        if (r.getAddress() != null) b.setAddress(r.getAddress());
        if (r.getPhoto() != null) b.setPhoto(r.getPhoto());

        // ✅ LIST → STRING conversion (CRITICAL)
        if (r.getCareers() != null && !r.getCareers().isEmpty()) {
            b.setCareers(String.join(",", r.getCareers()));
        }

        if (r.getHousing() != null) b.setHousing(r.getHousing());
        if (r.getJobAssistance() != null) b.setJobAssistance(r.getJobAssistance());
        if (r.getJobGuarantee() != null) b.setJobGuarantee(r.getJobGuarantee());
        if (r.getAcceptGi() != null) b.setAcceptGi(r.getAcceptGi());

        return repo.save(b);   // 🔥 FORCE UPDATE
    }


    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

