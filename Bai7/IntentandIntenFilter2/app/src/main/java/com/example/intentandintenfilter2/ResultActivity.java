package com.example.intentandintenfilter2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    EditText edtkq;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        edtkq = findViewById(R.id.txtketqua);
        btnback = findViewById(R.id.btnBack);

        Intent yourintent = getIntent();
        Bundle yourbundle = yourintent.getBundleExtra("mybackage");

        int a = yourbundle.getInt("soa");
        int b = yourbundle.getInt("sob");

        String kq = "";
        if (a == 0 && b == 0) {
            kq = "Vô số nghiệm";
        } else if (a == 0 && b != 0) {
            kq = "Vô nghiệm";
        } else {
            DecimalFormat dcf = new DecimalFormat("0.##");
            kq = dcf.format((double)-b / a); // Ép kiểu double để tính toán chính xác
        }
        edtkq.setText(kq);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}