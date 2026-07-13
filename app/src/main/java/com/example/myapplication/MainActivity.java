package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Shape myCircle = new Circle("Circle1", 2.5);
        Shape myRect = new Rectangle("Rect1", 20,10);
        Shape myTri = new Triangle("Tri1", 4,5);

        System.out.println("Name: "+ myCircle.getName()+ " Area: " + myCircle.area());
        System.out.println("Name: "+ myRect.getName()+ " Area: " + myRect.area());
        System.out.println("Name: "+ myTri.getName()+ " Area: " + myTri.area());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}