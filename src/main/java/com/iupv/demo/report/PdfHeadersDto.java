package com.iupv.demo.report;

import java.io.Serializable;

/**
 * DTO for {@link Report}
 */
public record PdfHeadersDto(String courseId, String courseName, String groupId, String hpUNIT, String lecturerId,
                            String lecturerName) implements Serializable {
}