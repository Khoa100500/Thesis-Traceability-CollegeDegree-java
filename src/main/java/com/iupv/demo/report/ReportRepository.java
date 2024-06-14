package com.iupv.demo.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Override
    @NonNull
    Optional<Report> findById(@NonNull Integer integer);

    List<Report> findByUser_UsernameOrderByTimePostedDesc(String username);

    List<Report> findByStudentScores_StudentIdOrderByTimePostedDesc(String studentId);


    long countByUser_Id(Integer id);
    long countByUser_Username(String username);
    List<Report> findByUser_Id(Integer id);
    List<Report> findByUser_Username(String username);

    List<Report> findByStudentScores_StudentId(String studentId);
}