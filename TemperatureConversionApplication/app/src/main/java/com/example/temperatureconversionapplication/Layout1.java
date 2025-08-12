package com.example.temperatureconversionapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class Layout1 extends AppCompatActivity {

    EditText Far,Cel;
    Button btnFar, btnCel, btnClr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layou1);
        Far = findViewById(R.id.txtFar);
        Cel = findViewById(R.id.txtCel);
        btnFar = findViewById(R.id.btnFar);
        btnCel = findViewById(R.id.btnCel);
        btnClr = findViewById(R.id.btnClear);
        btnCel.setOnClickListener(v -> {
            DecimalFormat dcf = new DecimalFormat("#00");
            String doC = Cel.getText()+"";
            int C=Integer.parseInt(doC);
            Cel.setText(dcf.format(C * 1.8 + 32));
        });
        btnFar.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            DecimalFormat dcf=new DecimalFormat("#.00");
            String doF = Far.getText()+"";
            //
            int F=Integer.parseInt(doF);
            Far.setText(dcf.format((F-32)/1.8));
        });
        btnClr.setOnClickListener(v ->{
            Far.setText("");
            Cel.setText("");
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}