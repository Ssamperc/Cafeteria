package com.cafeteria.mappers;

import com.cafeteria.entity.Producto;
import com.cafeteria.model.ProductoModel;
import com.cafeteria.model.ProductoModelV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    Producto modelToEntity(ProductoModel productoModel);

    ProductoModel entityToModel(Producto producto);

    ProductoModelV2 entityToModelV2(Producto producto);

    Producto modelV2toEntity(ProductoModelV2 productoModelV2);
}