package com.cafeteria.mappers;

import com.cafeteria.entity.Asesor;
import com.cafeteria.model.AsesorModel;
import com.cafeteria.model.AsesorModelV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AsesorMapper {

    Asesor modelToEntity(AsesorModel asesorModel);

    AsesorModel entityToModel(Asesor asesor);

    AsesorModelV2 entityToModelV2(Asesor asesor);

    Asesor modelV2toEntity(AsesorModelV2 asesorModelV2);
}