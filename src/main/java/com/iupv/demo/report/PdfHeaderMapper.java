package com.iupv.demo.report;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PdfHeaderMapper {
    Report toEntity(PdfHeadersDto pdfHeadersDto);

    PdfHeadersDto toDto(Report report);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Report partialUpdate(PdfHeadersDto pdfHeadersDto, @MappingTarget Report report);
}