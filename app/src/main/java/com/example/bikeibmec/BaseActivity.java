package com.example.bikeibmec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    void setHomeButton() {
        Button button = findViewById(R.id.homeButton);
        Intent homeIntent = new Intent(this, MainActivity.class);
        button.setOnClickListener(v -> {
            startActivity(homeIntent);
        });
    }
}