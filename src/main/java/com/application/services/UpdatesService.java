package com.application.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.data.Updates;
import com.application.data.UpdatesRepository;

@Service
public class UpdatesService {
    
    private final UpdatesRepository repository;
    
    public UpdatesService(UpdatesRepository repository) {
        this.repository = repository;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public Page<Updates> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }
    
    public Optional<Updates> get(Long id) {
        return repository.findById(id);
    }

    public Updates save(Updates entity) {
        return repository.save(entity);
    }

    public Page<Updates> list(Pageable pageable, String filter) {
        if (filter == null || filter.isEmpty()) {
            return repository.findAll(pageable);
        } else {
            return repository.findByTitleContainingIgnoreCase(filter, pageable);
        }
    }

}
