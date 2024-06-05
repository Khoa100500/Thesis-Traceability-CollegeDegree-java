package com.iupv.demo.score;

import com.iupv.demo.report.Report;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student_scores")
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "student_id", nullable = false, length = 11)
    private String studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "class_id", length = 20)
    private String classId;

    @Column(name = "inclass")
    private Integer inclass;

    @Column(name = "midterm")
    private Integer midterm;

    @Column(name = "final")
    private Integer finalField;

    @Column(name = "description")
    private String description;

}