package com.example.intentandintentfilter3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intentandintentfilter3.R;

public class ResultActivity extends AppCompatActivity {
    EditText editTextA, editTextB;
    Button buttonTong, buttonHieu;
    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        buttonTong = findViewById(R.id.buttonTong);
        buttonHieu = findViewById(R.id.buttonHieu);

        myintent = getIntent();

        Bundle yourbundle = myintent.getBundleExtra("mybackage");
        int a = yourbundle.getInt("soa", 0);
        int b = yourbundle.getInt("sob", 0);

        // Hiển thị giá trị a và b lên EditText
        editTextA.setText(String.valueOf(a));
        editTextB.setText(String.valueOf(b));

        buttonTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý kết quả (tổng)
                int sum = a + b;

                // Đẩy kết quả trở lại Intent
                myintent.putExtra("kq", sum);
                setResult(33, myintent);
                finish();
            }
        });

        buttonHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sub = a - b;

                myintent.putExtra("kq", sub);
                setResult(34, myintent);
                finish();
            }
        });
    }
}