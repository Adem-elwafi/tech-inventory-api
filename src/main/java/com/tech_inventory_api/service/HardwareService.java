package com.tech_inventory_api.service;

import com.tech_inventory_api.model.Hardware;
import com.tech_inventory_api.repository.HardwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Injecte automatiquement le repository via le constructeur
public class HardwareService {

    private final HardwareRepository hardwareRepository;

    public Hardware saveHardware(Hardware hardware) {
        // Règle métier : On ne peut pas avoir deux fois le même numéro de série
        if (hardwareRepository.existsBySerialNumber(hardware.getSerialNumber())) {
            throw new RuntimeException("Ce numéro de série existe déjà !");
        }
        return hardwareRepository.save(hardware);
    }

    public List<Hardware> getAllHardware() {
        return hardwareRepository.findAll();
    }

    public List<Hardware> getHardwareByType(String type) {
        // Conversion de String vers Enum
        return hardwareRepository.findByType(com.tech_inventory_api.model.DeviceType.valueOf(type.toUpperCase()));
    }
}