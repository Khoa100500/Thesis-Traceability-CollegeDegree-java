package com.iupv.demo.report;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link Report}
 */
public record StudentRecordDto(String courseId, String courseName, String lecturerId, String lecturerName,
                               Instant timePosted, Set<StudentScoreDto> studentScores) implements Serializable {
    /**
     * DTO for {@link com.iupv.demo.score.StudentScore}
     */
    public record StudentScoreDto(String studentName, String classId, Integer inclass, Integer midterm,
                                  Integer finalField, String description) implements Serializable {
    }
}