package com.proyecto.inventory_service.utils;

import com.proyecto.inventory_service.model.identities.Inventory;
import com.proyecto.inventory_service.repositories.InventoryRepository;
import com.proyecto.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader  implements CommandLineRunner {

    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;

    @Override

    public void run(String... args) throws Exception {
        log.info("Loading data from inventory");
        if(inventoryRepository.findAll().size() == 0){
            inventoryRepository.saveAll(
                    List.of(
                            Inventory.builder().sku("00001").quantity(10l).build(),
                            Inventory.builder().sku("00002").quantity(10l).build(),
                            Inventory.builder().sku("00003").quantity(10l).build(),
                            Inventory.builder().sku("00004").quantity(0l).build()
                    )
            );
        }
        log.info("Loading data from inventory");
    }
}
