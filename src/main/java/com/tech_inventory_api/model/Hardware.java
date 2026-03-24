package com.tech_inventory_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "hardware")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du matériel est obligatoire")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(message = "Le type d'appareil est obligatoire")
    @Enumerated(EnumType.STRING) // Stocke le nom (LAPTOP) au lieu de l'index (0)
    private DeviceType type;

    @NotBlank(message = "Le numéro de série est obligatoire")
    @Column(unique = true) // Empêche les doublons en base de données
    private String serialNumber;

    @PastOrPresent(message = "La date d'achat ne peut pas être dans le futur")
    private LocalDate purchaseDate;

    @Future(message = "La date de fin de garantie doit être dans le futur")
    private LocalDate warrantyExpirationDate;

    private boolean isOperational = true;
}