package com.example.myapplication;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FunctionalJavaDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World");

        //product list
        List <Product> products = Arrays.asList(
                new Product("Laptop", 25000, true),
                new Product("Mouse", 800, false),
                new Product("Keyboard", 1500, true),
                new Product("Monitor", 6000, true)
        );

        //filter in stock prdcs, convert names to Upper case and collect to a list
        List<String> availableProductNames = products.stream()
                .filter(Product::isInStock)
                .map(p -> p.getName().toUpperCase())
                .collect(Collectors.toList());

        Log.d(TAG, "Available Products: " + availableProductNames.toString());

        //calculate the total price in stock products
        double totalCost = products.stream()
                .filter(Product::isInStock)
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);

        Log.d(TAG, "Total cost of available products: "+ totalCost+ "TL");


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}