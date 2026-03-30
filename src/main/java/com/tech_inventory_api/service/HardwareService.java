package com.tech_inventory_api.service;

import com.tech_inventory_api.exception.DuplicateSerialNumberException;
import com.tech_inventory_api.exception.InvalidDeviceTypeException;
import com.tech_inventory_api.exception.ResourceNotFoundException;
import com.tech_inventory_api.model.DeviceType;
import com.tech_inventory_api.model.Hardware;
import com.tech_inventory_api.repository.HardwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor // Injecte automatiquement le repository via le constructeur
public class HardwareService {

    private final HardwareRepository hardwareRepository;

    public Hardware saveHardware(Hardware hardware) {
        // Règle métier : On ne peut pas avoir deux fois le même numéro de série
        if (hardwareRepository.existsBySerialNumber(hardware.getSerialNumber())) {
            throw new DuplicateSerialNumberException("Ce numéro de série existe déjà !");
        }
        return hardwareRepository.save(hardware);
    }

    public List<Hardware> getAllHardware() {
        return hardwareRepository.findAll();
    }

    public Hardware getHardwareById(Long id) {
        return hardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matériel introuvable avec l'id: " + id));
    }

    public List<Hardware> getHardwareByType(String type) {
        // Conversion de String vers Enum avec message d'erreur lisible
        DeviceType deviceType;
        try {
            deviceType = DeviceType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            String allowedTypes = Arrays.toString(DeviceType.values());
            throw new InvalidDeviceTypeException("Type invalide: " + type + ". Valeurs acceptées: " + allowedTypes);
        }

        return hardwareRepository.findByType(deviceType);
    }

    public Hardware updateHardware(Long id, Hardware hardware) {
        Hardware existingHardware = getHardwareById(id);

        if (hardwareRepository.existsBySerialNumberAndIdNot(hardware.getSerialNumber(), id)) {
            throw new DuplicateSerialNumberException("Ce numéro de série existe déjà !");
        }

        existingHardware.setName(hardware.getName());
        existingHardware.setType(hardware.getType());
        existingHardware.setSerialNumber(hardware.getSerialNumber());
        existingHardware.setPurchaseDate(hardware.getPurchaseDate());
        existingHardware.setWarrantyExpirationDate(hardware.getWarrantyExpirationDate());
        existingHardware.setOperational(hardware.isOperational());

        return hardwareRepository.save(existingHardware);
    }

    public void deleteHardware(Long id) {
        Hardware existingHardware = getHardwareById(id);
        hardwareRepository.delete(existingHardware);
    }
}