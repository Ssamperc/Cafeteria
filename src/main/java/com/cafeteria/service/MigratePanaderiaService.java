package com.cafeteria.service;

import com.cafeteria.dto.SyncAsesorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class MigratePanaderiaService {

    private final RestTemplate restTemplate;

    @Value("${app.sync.asesor-url}")
    private String baseUrl;

    public void sendAsesorToOtherService(SyncAsesorDTO dto) {
        try {
            HttpEntity<SyncAsesorDTO> entity = new HttpEntity<>(dto);

            ResponseEntity<Object> response = restTemplate.postForEntity(
                    baseUrl + "/create-sync",
                    entity,
                    Object.class
            );

            log.info("Asesor sincronizado correctamente: {}", response.getStatusCode());
        } catch (Exception e) {
            log.error("Error sincronizando asesor: {}", e.getMessage());
        }
    }
}