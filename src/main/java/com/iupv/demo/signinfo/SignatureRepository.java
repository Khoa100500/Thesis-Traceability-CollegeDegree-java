package com.iupv.demo.signinfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SignatureRepository extends JpaRepository<Signature, Integer> {
    boolean existsBySubjectAndSignedOn(String subject, LocalDate signedOn);

    Signature findBySubjectAndSignedOn(String subject, LocalDate signedOn);
}