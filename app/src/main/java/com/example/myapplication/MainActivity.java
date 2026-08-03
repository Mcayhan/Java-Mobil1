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

        inventory.addProduct(new Product("100", "Mechanical Keyboard", 1850.50));
        inventory.addProduct(new Product("101", "Curved Monitor", 6200.00));
        inventory.addProduct(new Product("102", "Wireless Mouse", 950.00));

        System.out.println("\n--- Search Test ---");
        Product found = inventory.searchProduct("100");
        System.out.println("Found Product: " + found);

        System.out.println("\n--- Remove Test ---");
        inventory.removeProduct("101");
        inventory.removeProduct("999");

        System.out.println("\n--- Sorted By Price ---");
        List<Product> sortedList = inventory.getProductsSortedByPrice();
        for (Product p : sortedList) {
            System.out.println(p);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}