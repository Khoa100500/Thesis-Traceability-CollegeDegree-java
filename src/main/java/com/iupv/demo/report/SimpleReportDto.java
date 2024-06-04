package com.iupv.demo.report;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Report}
 */
public record SimpleReportDto(Integer id, String courseName, String groupId,
                              Instant timePosted) implements Serializable {
}