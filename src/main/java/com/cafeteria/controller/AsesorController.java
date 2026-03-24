package com.cafeteria.controller;

import com.cafeteria.entity.Asesor;
import com.cafeteria.repository.AsesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesores")
@RequiredArgsConstructor
public class AsesorController {

    private final AsesorRepository asesorRepository;

    @GetMapping
    public List<Asesor> getAll() {
        return asesorRepository.findAll();
    }

    @PostMapping
    public Asesor create(@RequestBody Asesor asesor) {
        return asesorRepository.save(asesor);
    }
}