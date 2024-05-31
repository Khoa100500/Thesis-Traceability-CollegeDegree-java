package com.iupv.demo.signinfo;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SignatureMapper {
    Signature toEntity(SignatureInfoDto signatureInfoDto);

    SignatureInfoDto toDto(Signature signature);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Signature partialUpdate(SignatureInfoDto signatureInfoDto, @MappingTarget Signature signature);
}