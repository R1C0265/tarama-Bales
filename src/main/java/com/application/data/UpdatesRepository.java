package com.application.data;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UpdatesRepository extends JpaRepository<Updates, Long>, JpaSpecificationExecutor<Updates> {

    public Page<Updates> findByTitleContainingIgnoreCase(String filter, Pageable pageable);

    
}
