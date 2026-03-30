package com.cafeteria.dto;

import lombok.Data;

@Data
public class CreateProductoDTO {

    private String nombre;
    private String cantidad;
    private Integer existencia;

}
