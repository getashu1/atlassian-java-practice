package com.atlassian.practice.inventory;

import java.util.List;

public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public ReservationResult reserve(String sku, int quantity) {
        if (quantity <= 0) {
            return new ReservationResult(false, "Quantity must be positive", 0);
        }

        Product product = repository.findBySku(sku).orElse(null);
        if (product == null) {
            return new ReservationResult(false, "Product not found", 0);
        }

        int available = product.availableQuantity();
        if (available < quantity) {
            return new ReservationResult(false, "Insufficient inventory", available);
        }

        // Intentional bug for Exercise 1: reservation should reduce available inventory.
        int updatedQuantity = available + quantity;
        repository.save(new Product(product.sku(), product.name(), updatedQuantity));

        return new ReservationResult(true, "Reservation successful", updatedQuantity);
    }

    public Product getProduct(String sku) {
        return repository.findBySku(sku).orElse(null);
    }

    public List<Product> listProducts() {
        return repository.findAll();
    }
}
