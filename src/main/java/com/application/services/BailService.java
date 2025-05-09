package com.application.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.application.data.Bail;
import com.application.data.BailGrade;
import com.application.data.BailRepository;
import com.application.data.Updates;
import com.application.data.UpdatesRepository;
import com.application.security.SecurityUtils;

import jakarta.transaction.Transactional;

@Service
public class BailService {

    private final BailRepository bailRepository;
    private final UpdatesRepository updatesRepository;

    public BailService(BailRepository bailRepository, UpdatesRepository updatesRepository) {
        this.bailRepository = bailRepository;
        this.updatesRepository = updatesRepository;
    }

    public Optional<Bail> get(Long id) {
        return bailRepository.findById(id);
    }

    public Bail getBails(Bail entity){
        return bailRepository.findById(entity.getId()).orElseThrow(() -> new RuntimeException("Bail not found"));
    }

    public List<String> getBailName() {
        return bailRepository.findAllBailNames();
    }

    @Transactional
    public void delete(Long id) {
        Optional<Bail> bail = bailRepository.findById(id);
        if (bail.isPresent()) {
            // Add an update entry for the deletion
            Updates update = new Updates();
            update.setImage("https://randomuser.me/api/portraits/men/42.jpg"); // Example image
            update.setRecordedBy(SecurityUtils.getLoggedInUsername());
            update.setDate(LocalDate.now().toString());
            update.setTitle("Deleted a Bale");
            update.setCategory("Delete");
            update.setAmount("MWK " + bail.get().getBailPrice());
            updatesRepository.save(update);

            bailRepository.deleteById(id);
        }
    }

    public Page<Bail> list(Pageable pageable) {
        return bailRepository.findAll(pageable);
    }

    public Page<Bail> list(Pageable pageable, Specification<Bail> filter) {
        return bailRepository.findAll(filter, pageable);
    }

    public int count() {
        return (int) bailRepository.count();
    }

    public List<Bail> listAllBails() {
        return bailRepository.findAll();
    }
    
    @Transactional
    public Bail update(Bail bail) {
        boolean isNew = (bail.getId() == null); // Check if this is a new Bail
        if (bail.getRecordedBy() == null || bail.getRecordedBy().isEmpty()) {
            bail.setRecordedBy(SecurityUtils.getLoggedInUsername());
        }
        Bail savedBail = bailRepository.save(bail);

        Updates update = new Updates();
        // Add an update entry if this is a new Bail
        if (isNew) {
            update.setTitle("Added a new Bale");
            update.setCategory("Bale Added");
            update.setDate(LocalDate.now().toString());
            update.setAmount("MWK " + bail.getBailPrice());
            updatesRepository.save(update);
        }

        return savedBail;
        }

    @Transactional
    public void processSale(String bailName, int itemsSold) {
        Bail bail = bailRepository.findByBailName(bailName)
                .orElseThrow(() -> new RuntimeException("Bail not found"));
        if(bail.getAmounOfItems() < itemsSold) {
            throw new RuntimeException("Not enough items in stock");
        }
        bail.setAmounOfItems(bail.getAmounOfItems() - itemsSold);

        // Set the recordedBy field to the logged-in user
        bail.setRecordedBy(SecurityUtils.getLoggedInUsername());

        bailRepository.save(bail);
    }

    @Transactional
    public void addGradeToBail(Long bailId, BailGrade grade) {
        Bail bail = bailRepository.findById(bailId)
                .orElseThrow(() -> new RuntimeException("Bail not found"));
        grade.setBail(bail);
        // bail.getGrades().add(grade);

         // Set the recordedBy field to the logged-in user
        bail.setRecordedBy(SecurityUtils.getLoggedInUsername());

        bailRepository.save(bail);
    }
}
