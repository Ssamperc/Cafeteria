package com.cafeteria.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String cc;

    private Integer age;
}