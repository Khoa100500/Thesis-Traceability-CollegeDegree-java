package com.iupv.demo.score;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
    Set<StudentScore> findByStudentId(String studentId);
}