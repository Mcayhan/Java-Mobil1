package com.example.myapplication;

import java.util.*;

public class Inventory {
    private Map<String, Product> productMap;

    public Inventory() {
        this.productMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
        System.out.println("Added: " + product.getName());
    }

    public void removeProduct(String productId) {
        if (productMap.containsKey(productId)) {
            Product removed = productMap.remove(productId);
            System.out.println("Removed: " + removed.getName());
        } else {
            System.out.println("Error: Product with ID " + productId + " not found.");
        }
    }

    public Product searchProduct(String productId) {
        return productMap.get(productId);
    }

    public List<Product> getProductsSortedByPrice() {
        List<Product> productList = new ArrayList<>(productMap.values());
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        return productList;
    }
}
