package com.example.bi18;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.database.Cursor;
import android.view.View;
import android.content.ContentValues;

public class ActivitySub extends AppCompatActivity {

    TextView txtmaso, txttieude, txtloibai, txttacgia, txttheloai;
    ImageButton btnThich, btnKhongThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        txtmaso = findViewById(R.id.txtmaso);
        txttieude = findViewById(R.id.txttieude);
        txtloibai = findViewById(R.id.txtloibai);
        txttacgia = findViewById(R.id.txttacgia);
        txttheloai = findViewById(R.id.txttheloai);
        btnThich = findViewById(R.id.btnThich);
        btnKhongThich = findViewById(R.id.btnKhongThich);

        String maso = getIntent().getStringExtra("maso");
        loadDetail(maso);

        btnKhongThich.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update("ArirangSongList", values, "MABH = ?", new String[]{maso});
                btnKhongThich.setVisibility(View.INVISIBLE);
                btnThich.setVisibility(View.VISIBLE);
            }
        });

        btnThich.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update("ArirangSongList", values, "MABH = ?", new String[]{maso});
                btnKhongThich.setVisibility(View.VISIBLE);
                btnThich.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void loadDetail(String maso) {
        Cursor c = MainActivity.database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH = ?", new String[]{maso});
        if (c != null && c.moveToFirst()) {
            txtmaso.setText(c.getString(1));
            txttieude.setText(c.getString(2));
            txtloibai.setText(c.getString(3));
            txttacgia.setText(c.getString(4));
            txttheloai.setText(c.getString(5));
            int y = c.getInt(6);
            if (y == 1) {
                btnThich.setVisibility(View.VISIBLE);
                btnKhongThich.setVisibility(View.INVISIBLE);
            } else {
                btnThich.setVisibility(View.INVISIBLE);
                btnKhongThich.setVisibility(View.VISIBLE);
            }
            c.close();
        }
    }
}

