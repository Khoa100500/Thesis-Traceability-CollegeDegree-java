package com.iupv.demo.report;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SignatureMapper {
    Signature toEntity(SignatureDto signatureDto);

    @AfterMapping
    default void linkReports(@MappingTarget Signature signature) {
        signature.getReports().forEach(report -> report.setSign(signature));
    }

    SignatureDto toDto(Signature signature);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Signature partialUpdate(SignatureDto signatureDto, @MappingTarget Signature signature);
}