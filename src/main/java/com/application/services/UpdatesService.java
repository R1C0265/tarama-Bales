package com.application.services;

import org.springframework.stereotype.Service;

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

    public int count() {
        return (int) repository.count();
    }
}
