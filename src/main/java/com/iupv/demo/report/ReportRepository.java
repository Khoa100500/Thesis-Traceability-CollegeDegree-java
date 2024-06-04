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

    @Query("select r from Report r JOIN FETCH r.user where r.id = ?1 ")
    Optional<Report> findWithId(Integer id);


    long countByUser_Id(Integer id);
    long countByUser_Username(String username);
    List<Report> findByUser_Id(Integer id);
    List<Report> findByUser_Username(String username);
}