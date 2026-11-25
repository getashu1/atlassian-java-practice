package com.atlassian.practice.inventory;

public record Product(String sku, String name, int availableQuantity) {
    public Product {
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("sku is required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        if (availableQuantity < 0) throw new IllegalArgumentException("quantity cannot be negative");
    }
}
