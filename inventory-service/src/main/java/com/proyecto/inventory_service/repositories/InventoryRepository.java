package com.proyecto.inventory_service.repositories;

import com.proyecto.inventory_service.model.identities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

   Optional<Inventory>  findBySku(String sku);

    List<Inventory> findBySkuIn(List<String> skus);
}
