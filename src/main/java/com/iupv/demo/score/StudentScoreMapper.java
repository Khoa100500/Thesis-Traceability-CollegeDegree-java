package com.iupv.demo.score;

import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentScoreMapper {

    Set<StudentScore> toEntity(Set<StudentScoreDto> studentScoreDto);

    Set<StudentScoreDto> toDto(Set<StudentScore> studentScore);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)StudentScore partialUpdate(StudentScoreDto studentScoreDto, @MappingTarget StudentScore studentScore);
}