package com.atlassian.practice.inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository {
    Optional<Product> findBySku(String sku);
    void save(Product product);
    List<Product> findAll();
}
