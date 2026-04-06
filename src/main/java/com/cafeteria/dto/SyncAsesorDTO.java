package com.cafeteria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncAsesorDTO {
    private String id;
    private String nombre;
    private String cc;
    private Integer age;
    private String evidence;
}