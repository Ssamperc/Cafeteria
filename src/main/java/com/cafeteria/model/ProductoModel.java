package com.cafeteria.model;

import com.cafeteria.entity.Categoria;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProductoModel {

    private Long id;
    private String nombre;
    private Integer cantidad;
    private Integer existencia;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;
    private Categoria categoria;
}
