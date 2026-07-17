package com.example.myapplication;

import java.util.*;

public class Inventory {
    // Ürünleri Map içinde saklıyoruz (Key: ID, Value: Product)
    private Map<Integer, Product> productMap;

    public Inventory() {
        productMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
        System.out.println("Added: " + product.getName());
    }

    public void removeProduct(int id) {
        if (productMap.containsKey(id)) {
            Product removed = productMap.remove(id);
            System.out.println("Removed: " + removed.getName());
        } else {
            System.out.println("Product not found!");
        }
    }

    public Product searchProduct(int id) {
        return productMap.get(id);
    }

    // Map içindeki verileri ArrayList'e çevirip fiyata göre sıralıyoruz
    public List<Product> getProductsSortedByPrice() {
        List<Product> productList = new ArrayList<>(productMap.values());
        Collections.sort(productList);
        return productList;
    }

    // Iterator kullanımını gösteren metot
    public void printAll() {
        System.out.println("--- All Products ---");
        Iterator<Product> iterator = productMap.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
