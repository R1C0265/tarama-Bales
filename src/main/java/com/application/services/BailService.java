package com.application.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.application.data.Bail;
import com.application.data.BailRepository;

@Service
public class BailService {

    private final BailRepository repository;

    public BailService(BailRepository repository) {
        this.repository = repository;
    }

    public Optional<Bail> get(Long id) {
        return repository.findById(id);
    }

    public Bail update(Bail entity) {
        return repository.save(entity);
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

}
