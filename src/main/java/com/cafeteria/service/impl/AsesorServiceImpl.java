package com.cafeteria.service.impl;

import com.cafeteria.dto.CreateAsesorDTO;
import com.cafeteria.dto.SyncAsesorDTO;
import com.cafeteria.entity.Asesor;
import com.cafeteria.mappers.AsesorMapper;
import com.cafeteria.model.AsesorModel;
import com.cafeteria.model.AsesorModelV2;
import com.cafeteria.repository.AsesorRepository;
import com.cafeteria.service.AsesorService;
import com.cafeteria.service.MigratePanaderiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsesorServiceImpl implements AsesorService {

    private final AsesorRepository asesorRepository;
    private final AsesorMapper asesorMapper;
    private final MigratePanaderiaService migratePanaderiaService;

    @Value("${app.files.asesor.upload-dir}")
    private String uploadDir;

    @Override
    public AsesorModel createAsesor(CreateAsesorDTO createAsesorDTO) {
        String id = UUID.randomUUID().toString();

        Asesor asesor = new Asesor();
        asesor.setId(id);
        asesor.setNombre(createAsesorDTO.getNombre());
        asesor.setCc(createAsesorDTO.getCc());
        asesor.setAge(createAsesorDTO.getAge());

        Asesor saved = asesorRepository.save(asesor);

        SyncAsesorDTO syncDto = new SyncAsesorDTO(
                saved.getId(),
                saved.getNombre(),
                saved.getCc(),
                saved.getAge(),
                saved.getEvidence()
        );
        migratePanaderiaService.sendAsesorToOtherService(syncDto);

        return asesorMapper.entityToModel(saved);
    }

    @Override
    public AsesorModelV2 createAsesorV2(CreateAsesorDTO createAsesorDTO) {
        String id = UUID.randomUUID().toString();

        Asesor asesor = new Asesor();
        asesor.setId(id);
        asesor.setNombre(createAsesorDTO.getNombre());
        asesor.setCc(createAsesorDTO.getCc());
        asesor.setAge(createAsesorDTO.getAge());

        Asesor saved = asesorRepository.save(asesor);

        SyncAsesorDTO syncDto = new SyncAsesorDTO(
                saved.getId(),
                saved.getNombre(),
                saved.getCc(),
                saved.getAge(),
                saved.getEvidence()
        );
        migratePanaderiaService.sendAsesorToOtherService(syncDto);

        return asesorMapper.entityToModelV2(saved);
    }

    @Override
    public AsesorModel createAsesorWithFile(String nombre, String cc, Integer age, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("El archivo es obligatorio");
        }

        try {
            byte[] fileBytes = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(fileBytes);

            Asesor asesor = new Asesor();
            asesor.setId(UUID.randomUUID().toString());
            asesor.setNombre(nombre);
            asesor.setCc(cc);
            asesor.setAge(age);
            asesor.setEvidence(base64);

            Asesor saved = asesorRepository.save(asesor);
            return asesorMapper.entityToModel(saved);
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir el archivo a Base64", e);
        }
    }

    @Override
    public AsesorModelV2 getAsesorById(String id) {
        Asesor asesor = asesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un asesor con id: " + id));

        return asesorMapper.entityToModelV2(asesor);
    }

    @Override
    public Page<AsesorModel> getAllAsesores(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return asesorRepository.findAll(pageRequest).map(asesorMapper::entityToModel);
    }

    @Override
    public List<AsesorModelV2> getAllAsesoresV2() {
        return asesorRepository.findAll()
                .stream()
                .map(asesorMapper::entityToModelV2)
                .toList();
    }

    @Override
    public Page<AsesorModelV2> getAllAsesoresV2(int page, int size, String nombre) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return asesorRepository.findByNombreContainingIgnoreCase(nombre, pageRequest)
                .map(asesorMapper::entityToModelV2);
    }
}