package com.application.data;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BailRepository extends JpaRepository<Bail, Long>, JpaSpecificationExecutor<Bail> {
    @Query("SELECT b.bailName FROM Bail b")
        List<String> findAllBailNames();
    
    @Query("SELECT b FROM Bail b WHERE b.bailName = :bailName")
        Optional<Bail> findByBailName(String bailName);
}
