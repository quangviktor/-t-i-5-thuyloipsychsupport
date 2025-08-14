package com.example.bai21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewSanPham;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private ArrayAdapter<SanPham> sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSanPham = findViewById(R.id.listViewSanPham);

        sanPhamAdapter = new SanPhamAdapter(this, listSanPham);
        listViewSanPham.setAdapter(sanPhamAdapter);

        parseJson();
    }

    private void parseJson() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("computer.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            // Phân tích JSON
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("sangphams");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                String maSP = obj.getString("MaSP");
                String tenSP = obj.getString("TenSP");
                String soLuong = obj.getString("SoLuong");
                String donGia = obj.getString("DonGia");
                String thanhTien = obj.getString("ThanhTien");
                String hinh = obj.getString("Hinh");

                SanPham sanPham = new SanPham(maSP, tenSP, soLuong, donGia, thanhTien, hinh);
                listSanPham.add(sanPham);
            }

            // Cập nhật ListView
            sanPhamAdapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading JSON file", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error parsing JSON file", Toast.LENGTH_SHORT).show();
        }
    }
}
