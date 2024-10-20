package com.proyecto.inventory_service.model.identities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private Long quantity;
}
