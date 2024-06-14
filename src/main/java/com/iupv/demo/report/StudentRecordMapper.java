package com.iupv.demo.report;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentRecordMapper {
    Report toEntity(StudentRecordDto studentRecordDto);

    @AfterMapping
    default void linkStudentScores(@MappingTarget Report report) {
        report.getStudentScores().forEach(studentScore -> studentScore.setReport(report));
    }

    StudentRecordDto toDto(Report report);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Report partialUpdate(StudentRecordDto studentRecordDto, @MappingTarget Report report);

    List<Report> toEntity(List<StudentRecordDto> studentRecordDto);

    List<StudentRecordDto> toDto(List<Report> report);
}