package com.cafeteria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "asesor")
@Data
public class Asesor {

    @Id
    private String id;

    private String nombre;

    private String cc;

    private Integer age;

    private String evidence;
}