package com.iupv.demo.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Override
    @NonNull
    Optional<Report> findById(@NonNull Integer integer);
}