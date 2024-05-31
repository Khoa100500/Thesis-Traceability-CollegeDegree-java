package com.iupv.demo.score;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link StudentScore}
 */
public record StudentScoreDto(String studentId, String studentName, LocalDate birthday, String classId, Integer inclass, Integer midterm, Integer finalField, String description) implements Serializable {
  }