package com.iupv.demo.report;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SimpleReportMapper {
    Report toEntity(SimpleReportDto simpleReportDto);

    SimpleReportDto toDto(Report report);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Report partialUpdate(SimpleReportDto simpleReportDto, @MappingTarget Report report);

    List<Report> toEntity(List<SimpleReportDto> simpleReportDto);

    List<SimpleReportDto> toDto(List<Report> report);
}