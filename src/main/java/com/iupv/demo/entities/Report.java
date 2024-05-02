package com.iupv.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID report_id;
}
