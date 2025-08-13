package com.example.intentvintentfilter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChildActivity extends AppCompatActivity {
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnMain = findViewById(R.id.btnMain);
        btnMain.setOnClickListener(v -> {
            Intent intent2 = new Intent(ChildActivity.this, MainActivity.class);
            startActivity(intent2);
        });
    }
}

