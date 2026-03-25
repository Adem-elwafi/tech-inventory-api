package com.tech_inventory_api.controller;

import com.tech_inventory_api.model.Hardware;
import com.tech_inventory_api.model.DeviceType;
import com.tech_inventory_api.service.HardwareService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hardware")
@RequiredArgsConstructor
public class HardwareController {

    private final HardwareService hardwareService;

    // Créer un nouvel appareil
    @PostMapping
    public ResponseEntity<Hardware> createHardware(@Valid @RequestBody Hardware hardware) {
        Hardware savedHardware = hardwareService.saveHardware(hardware);
        return new ResponseEntity<>(savedHardware, HttpStatus.CREATED);
    }

    // Récupérer tous les appareils
    @GetMapping
    public ResponseEntity<List<Hardware>> getAll() {
        return ResponseEntity.ok(hardwareService.getAllHardware());
    }

    // Filtrer par type (ex: /api/hardware/type/LAPTOP)
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Hardware>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(hardwareService.getHardwareByType(type));
    }
}