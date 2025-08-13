package com.example.intentandintenfilter2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intentandintenfilter2.R;
import com.example.intentandintenfilter2.ResultActivity;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb;
    Button btnkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edta = findViewById(R.id.txta);
        edtb = findViewById(R.id.txtb);
        btnkq = findViewById(R.id.btnketqua);

        btnkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, ResultActivity.class);

                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());

                Bundle bundle1 = new Bundle();
                bundle1.putInt("soa", a);
                bundle1.putInt("sob", b);
                myintent.putExtra("mybackage", bundle1);

                startActivity(myintent);
            }
        });
    }
}