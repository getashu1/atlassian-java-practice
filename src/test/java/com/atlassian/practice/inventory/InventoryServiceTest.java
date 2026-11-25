package com.atlassian.practice.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {
    private InMemoryInventoryRepository repository;
    private InventoryService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryInventoryRepository();
        repository.save(new Product("SKU-200", "Keyboard", 10));
        repository.save(new Product("SKU-100", "Mouse", 3));
        service = new InventoryService(repository);
    }

    @Test
    void reservationReducesAvailableInventory() {
        ReservationResult result = service.reserve("SKU-200", 4);

        assertTrue(result.successful());
        assertEquals(6, result.remainingQuantity());
        assertEquals(6, service.getProduct("SKU-200").availableQuantity());
    }

    @Test
    void reservationFailsWhenInventoryIsInsufficient() {
        ReservationResult result = service.reserve("SKU-100", 5);

        assertFalse(result.successful());
        assertEquals(3, service.getProduct("SKU-100").availableQuantity());
    }

    @Test
    void reservationRejectsNonPositiveQuantity() {
        ReservationResult result = service.reserve("SKU-200", 0);

        assertFalse(result.successful());
        assertEquals(10, service.getProduct("SKU-200").availableQuantity());
    }

    @Test
    void reservationFailsForUnknownSku() {
        ReservationResult result = service.reserve("UNKNOWN", 1);

        assertFalse(result.successful());
    }
}
