package com.application.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BailRepository extends JpaRepository<Bail, Long>, JpaSpecificationExecutor<Bail> {

}
