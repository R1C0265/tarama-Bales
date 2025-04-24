package com.application.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UpdatesRepository extends JpaRepository<Updates, Long>, JpaSpecificationExecutor<Updates> {

    
}
