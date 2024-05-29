package com.iupv.demo.report;

import com.iupv.demo.User.User;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link Report}
 */
public record ReportDto(Integer id, User user, SignatureDto sign, String courseId, String courseName, String groupId,
                        String hpUNIT, String lecturerId, String lecturerName, Instant timePosted,
                        Set<StudentScoreDto> studentScores) implements Serializable {
}