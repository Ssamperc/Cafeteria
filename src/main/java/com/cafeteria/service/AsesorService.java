package com.cafeteria.service;

import com.cafeteria.dto.CreateAsesorDTO;
import com.cafeteria.model.AsesorModel;
import com.cafeteria.model.AsesorModelV2;
import org.springframework.data.domain.Page;

public interface AsesorService {

    AsesorModel createAsesor(CreateAsesorDTO createAsesorDTO);

    AsesorModelV2 createAsesorV2(CreateAsesorDTO createAsesorDTO);

    AsesorModelV2 getAsesorById(Long id);

    Page<AsesorModel> getAllAsesores(int page, int size);
}