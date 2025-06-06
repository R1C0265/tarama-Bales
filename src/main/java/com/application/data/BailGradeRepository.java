package com.application.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BailGradeRepository extends JpaRepository<BailGrade, Long> {
    @Query("SELECT b.gradeNumber FROM BailGrade b")
        List<BailGrade> findAllGradeNumbers();
}