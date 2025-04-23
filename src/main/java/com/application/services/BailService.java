package com.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.application.data.Bail;
import com.application.data.BailGrade;
import com.application.data.BailRepository;

import jakarta.transaction.Transactional;

@Service
public class BailService {

    private final BailRepository repository;

    public BailService(BailRepository repository) {
        this.repository = repository;
    }

    public Optional<Bail> get(Long id) {
        return repository.findById(id);
    }

    public Bail getBails(Bail entity){
        return repository.findById(entity.getId()).orElseThrow(() -> new RuntimeException("Bail not found"));
    }

    public Bail update(Bail entity) {
        return repository.save(entity);
    }

    public List<String> getBailName() {
        return repository.findAllBailNames();
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Bail> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Bail> list(Pageable pageable, Specification<Bail> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    public List<Bail> listAllBails() {
        return repository.findAll();
    }

    @Transactional
    public void processSale(String bailName, int itemsSold) {
        Bail bail = repository.findByBailName(bailName)
                .orElseThrow(() -> new RuntimeException("Bail not found"));
        if(bail.getAmounOfItems() < itemsSold) {
            throw new RuntimeException("Not enough items in stock");
        }
        bail.setAmounOfItems(bail.getAmounOfItems() - itemsSold);
        repository.save(bail);
    }

    @Transactional
    public void addGradeToBail(Long bailId, BailGrade grade) {
        Bail bail = repository.findById(bailId)
                .orElseThrow(() -> new RuntimeException("Bail not found"));
        grade.setBail(bail);
        // bail.getGrades().add(grade);
        repository.save(bail);
    }
}
