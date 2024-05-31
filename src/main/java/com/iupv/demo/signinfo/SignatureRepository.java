package com.iupv.demo.signinfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signature, Integer> {
}