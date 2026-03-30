package com.cafeteria.controller;

import com.cafeteria.dto.CreateAsesorDTO;
import com.cafeteria.model.AsesorModel;
import com.cafeteria.model.AsesorModelV2;
import com.cafeteria.service.AsesorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesores")
@RequiredArgsConstructor
public class AsesorController {

    private final AsesorService asesorService;

    @PostMapping(value = "/create")
    @Operation(summary = "Endpoint que se encarga de realizar la creacion de asesores - V1")
    public AsesorModel create(@RequestBody CreateAsesorDTO asesor) {
        return asesorService.createAsesor(asesor);
    }

    @PostMapping(value = "/create-v2")
    @Operation(summary = "Endpoint que se encarga de realizar la creacion de asesores - V2")
    public AsesorModelV2 createV2(@RequestBody CreateAsesorDTO asesor) {
        return asesorService.createAsesorV2(asesor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar asesor por ID y retornar solo ID y nombre")
    public AsesorModelV2 getById(@PathVariable Long id) {
        return asesorService.getAsesorById(id);
    }

    @GetMapping("/page/{page}/{size}")
    @Operation(summary = "Buscar todos los asesores")
    public Page<AsesorModel> getAllAsesores(@PathVariable int page, @PathVariable int size) {
        return asesorService.getAllAsesores(page, size);
    }

    @GetMapping("/asesores/v2")
    @Operation(summary = "Mostrar todos los asesores")
    public List<AsesorModelV2> getAllAsesoresV2() {
        return asesorService.getAllAsesoresV2();
    }

    @GetMapping("filter/page/{page}/{size}")
    @Operation(summary = "Buscar asesores paginados por nombre")
    public Page<AsesorModelV2> getAllAsesoresV2(@PathVariable int page, @PathVariable int size, @RequestParam(required = false) String nombre) {
        return asesorService.getAllAsesoresV2(page, size, nombre);
    }
}