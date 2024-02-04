package com.pragma.powerup_usermicroservice.application.mapper;

import com.pragma.powerup_usermicroservice.application.dto.request.ObjectRequestDto;
import com.pragma.powerup_usermicroservice.domain.model.ObjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectRequestMapper {
    ObjectModel toObject(ObjectRequestDto objectRequestDto);
}
