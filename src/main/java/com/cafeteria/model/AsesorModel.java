package com.cafeteria.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AsesorModel {

    private String id;
    private String nombre;
    private String cc;
    private Integer age;
    private String evidence;
    private LocalDate creationDate;
    private LocalDate modifationDate;
}