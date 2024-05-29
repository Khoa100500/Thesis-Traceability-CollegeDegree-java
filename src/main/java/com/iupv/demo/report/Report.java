package com.iupv.demo.report;

import com.iupv.demo.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sign_id", nullable = false)
    private Signature sign;

    @Column(name = "course_id", nullable = false, length = 10)
    private String courseId;

    @Column(name = "course_name", nullable = false, length = 50)
    private String courseName;

    @Column(name = "group_id", nullable = false, length = 5)
    private String groupId;

    @Column(name = "hpUNIT", nullable = false, length = 10)
    private String hpUNIT;

    @Column(name = "lecturer_id", nullable = false, length = 10)
    private String lecturerId;

    @Column(name = "lecturer_name", nullable = false)
    private String lecturerName;

    @Column(name = "time_posted")
    private Instant timePosted;

    @OneToMany(mappedBy = "report")
    private Set<StudentScore> studentScores = new LinkedHashSet<>();

}