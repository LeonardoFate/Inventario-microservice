package com.proyecto.inventory_service.service;


import com.proyecto.inventory_service.model.dtos.BaseResponse;
import com.proyecto.inventory_service.model.dtos.OrderItemRequest;
import com.proyecto.inventory_service.model.identities.Inventory;
import com.proyecto.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku) {
        var inventory = inventoryRepository.findBySku(sku);

        return inventory.filter(value -> value.getQuantity() > 0).isPresent();

    }

    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {
        var errorList = new ArrayList<String>();

        List<String> skus = orderItems.stream().map(OrderItemRequest::getSku).toList();

        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream().filter(inventory1 -> inventory1.getSku().equals(orderItem.getSku())).findFirst();
            if (inventory.isPresent()) {
                errorList.add("SKU " + orderItem.getSku() + " is already in stock");
            }else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                errorList.add("SKU " + orderItem.getSku() + " is out of stock");
            }
        });
        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }
}
