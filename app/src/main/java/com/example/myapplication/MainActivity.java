package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World");
        Inventory inventory = new Inventory();

        Product p1 = new Product(101, "Laptop", 1500.0);
        Product p2 = new Product(102, "Mouse", 25.0);
        Product p3 = new Product(103, "Keyboard", 75.0);

        inventory.addProduct(p1);
        inventory.addProduct(p2);
        inventory.addProduct(p3);

        System.out.println("\nSearching for ID 102:");
        System.out.println(inventory.searchProduct(102));

        System.out.println();
        inventory.printAll();

        System.out.println("\n--- Sorted by Price ---");
        List<Product> sortedList = inventory.getProductsSortedByPrice();
        for (Product p : sortedList) {
            System.out.println(p);
        }

        System.out.println();
        inventory.removeProduct(101);
        inventory.printAll();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}