package com.iupv.demo.report;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportMapper {
    Report toEntity(ReportDto reportDto);

    @AfterMapping
    default void linkStudentScores(@MappingTarget Report report) {
        report.getStudentScores().forEach(studentScore -> studentScore.setReport(report));
    }

    ReportDto toDto(Report report);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Report partialUpdate(ReportDto reportDto, @MappingTarget Report report);
}