package com.iupv.demo.report;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link StudentScore}
 */
public record StudentScoreDto(Integer id, Report report, String studentId, String studentName, LocalDate birthday,
                              String classId, Byte inclass, Byte midterm, Byte finalField,
                              String description) implements Serializable {
}