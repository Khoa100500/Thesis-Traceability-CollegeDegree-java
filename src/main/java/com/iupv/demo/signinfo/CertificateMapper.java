package com.iupv.demo.signinfo;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CertificateMapper {
    Signature toEntity(CertificateInfoDto certificateInfoDto);

    CertificateInfoDto toDto(Signature signature);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Signature partialUpdate(CertificateInfoDto certificateInfoDto, @MappingTarget Signature signature);
}