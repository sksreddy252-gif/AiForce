package com.example.commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.commerce.inventory.InventoryService;
import org.junit.jupiter.api.Test;

class InventoryServiceTest {

    @Test
    void reserveStock_shouldReduceInventory() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock(new com.example.commerce.domain.Product("SKU-900", "Headphones", com.example.commerce.domain.Category.ELECTRONICS, 150.00, 10), 10);

        inventoryService.reserveStock("SKU-900", 3);

        assertEquals(7, inventoryService.getAvailableStock("SKU-900"));
    }

    @Test
    void reserveStock_shouldRejectInsufficientStock() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock(new com.example.commerce.domain.Product("SKU-901", "Keyboard", com.example.commerce.domain.Category.ELECTRONICS, 80.00, 2), 2);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> inventoryService.reserveStock("SKU-901", 3)
        );

        assertTrue(exception.getMessage().contains("Insufficient stock"));
    }
}
