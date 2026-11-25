package com.atlassian.practice.inventory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryInventoryRepository implements InventoryRepository {
    private final Map<String, Product> products = new LinkedHashMap<>();

    @Override
    public Optional<Product> findBySku(String sku) {
        return Optional.ofNullable(products.get(sku));
    }

    @Override
    public void save(Product product) {
        products.put(product.sku(), product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
