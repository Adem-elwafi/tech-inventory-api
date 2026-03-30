package com.tech_inventory_api.repository;


import com.tech_inventory_api.model.Hardware;

import com.tech_inventory_api.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
    // Requête dérivée : Spring génère automatiquement le SQL pour nous !
    List<Hardware> findByType(DeviceType type);

    // Pour vérifier si un numéro de série existe déjà
    boolean existsBySerialNumber(String serialNumber);

    // Vérifie l'unicité du serial lors d'une mise à jour
    boolean existsBySerialNumberAndIdNot(String serialNumber, Long id);
}