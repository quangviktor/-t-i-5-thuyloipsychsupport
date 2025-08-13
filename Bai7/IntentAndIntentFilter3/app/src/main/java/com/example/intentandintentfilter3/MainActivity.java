package com.example.intentandintentfilter3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editTextA, editTextB;
    TextView textViewResult;
    Button buttonRequestResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        textViewResult = findViewById(R.id.textViewResult);
        buttonRequestResult = findViewById(R.id.buttonRequestResult);

        buttonRequestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myintent = new Intent(MainActivity.this, ResultActivity.class);

                int a = Integer.parseInt(editTextA.getText().toString());
                int b = Integer.parseInt(editTextB.getText().toString());

                Bundle bundle1 = new Bundle();
                bundle1.putInt("soa", a);
                bundle1.putInt("sob", b);
                myintent.putExtra("mybackage", bundle1);

                startActivityForResult(myintent, 99);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99 && resultCode == 33) {
            int sum = data.getIntExtra("kq", 0);
            textViewResult.setText("Tổng 2 số là: " + sum);
        }
        if (requestCode == 99 && resultCode == 34) {
            int sub = data.getIntExtra("kq", 0);
            textViewResult.setText("Hiệu 2 số là: " + sub);
        }
    }
}