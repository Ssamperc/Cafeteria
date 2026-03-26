package com.cafeteria.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AsesorModel {

    private long id;
    private String nombre;
    private String cc;
    private Integer age;
    private LocalDate creationDate;
    private LocalDate modifationDate;
}
