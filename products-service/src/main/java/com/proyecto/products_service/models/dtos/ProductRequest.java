package com.proyecto.products_service.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductRequest {
    private String sku;
    private String name;
    private String description;
    private double price;
    private boolean status;
}
