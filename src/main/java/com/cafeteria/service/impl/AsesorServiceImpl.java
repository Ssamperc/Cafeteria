package com.cafeteria.service.impl;


import com.cafeteria.dto.CreateAsesorDTO;
import com.cafeteria.entity.Asesor;
import com.cafeteria.mappers.AsesorMapper;
import com.cafeteria.model.AsesorModel;
import com.cafeteria.model.AsesorModelV2;
import com.cafeteria.repository.AsesorRepository;
import com.cafeteria.service.AsesorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AsesorServiceImpl implements AsesorService {

    private final AsesorRepository asesorRepository;
    private final AsesorMapper asesorMapper;

    @Override
    public AsesorModel createAsesor(CreateAsesorDTO createAsesorDTO) {
        AsesorModel asesorModel = new AsesorModel();
        asesorModel.setNombre(createAsesorDTO.getNombre());
        asesorModel.setCc(createAsesorDTO.getCc());
        asesorModel.setAge(createAsesorDTO.getAge());
        asesorModel.setCreationDate(LocalDate.now());
        asesorModel.setModifationDate(LocalDate.now());

        Asesor asesorEntity = asesorMapper.modelToEntity(asesorModel);
        Asesor savedAsesor = asesorRepository.save(asesorEntity);

        AsesorModel response = asesorMapper.entityToModel(savedAsesor);
        response.setCreationDate(asesorModel.getCreationDate());
        response.setModifationDate(asesorModel.getModifationDate());

        return response;
    }

    @Override
    public AsesorModelV2 createAsesorV2(CreateAsesorDTO createAsesorDTO) {
        AsesorModelV2 asesorModel = new AsesorModelV2();
        asesorModel.setNombre(createAsesorDTO.getNombre());

        Asesor asesorEntity = asesorMapper.modelV2toEntity(asesorModel);
        Asesor savedAsesor = asesorRepository.save(asesorEntity);

        asesorModel.setId(savedAsesor.getId());
        return asesorModel;
    }

    @Override
    public AsesorModelV2 getAsesorById(Long id) {
        Asesor asesor = asesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un asesor con id: " + id));

        return asesorMapper.entityToModelV2(asesor);
    }

    @Override
    public Page<AsesorModel> getAllAsesores(int page, int size) {
        PageRequest pagerequest = PageRequest.of(page, size);

        return asesorRepository.findAll(pagerequest).map(asesorMapper::entityToModel);
    }
}