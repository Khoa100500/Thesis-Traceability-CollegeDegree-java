package com.iupv.demo.report;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentScoreMapper {
    StudentScore toEntity(StudentScoreDto studentScoreDto);

    StudentScoreDto toDto(StudentScore studentScore);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentScore partialUpdate(StudentScoreDto studentScoreDto, @MappingTarget StudentScore studentScore);
}