package com.proyecto.inventory_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    private Long Id;
    private String sku;
    private double price;
    private Long quantity;
}
