package com.iupv.demo.report;

import com.iupv.demo.score.StudentScoreDto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link Report}
 */
public record ReportDto(UserDto user, String courseId, String courseName, String groupId, String hpUNIT,
                        String lecturerId, String lecturerName, Instant timePosted, Set<StudentScoreDto> studentScores,
                        SignatureDto sign) implements Serializable {
}