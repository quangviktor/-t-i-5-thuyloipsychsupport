package com.example.bai15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editMaLop, editTenLop, editSiSo;
    Button btnInsert, btnUpdate, btnDelete, btnQuery;
    ListView listView;

    MyDatabase myDatabase;
    SQLiteDatabase db;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addControl() {
        editMaLop = (EditText) findViewById(R.id.editMaLop);
        editTenLop = (EditText) findViewById(R.id.editTenLop);
        editSiSo = (EditText) findViewById(R.id.editSiSo);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnQuery = (Button) findViewById(R.id.btnQuery);

        listView = (ListView) findViewById(R.id.listView);

        myDatabase = new MyDatabase(this);
        db = myDatabase.getWritableDatabase();

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    private void addEvent() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }

    private void insert() {
        ContentValues values = new ContentValues();
        values.put("malop", editMaLop.getText().toString());
        values.put("tenlop", editTenLop.getText().toString());
        values.put("siso", Integer.parseInt(editSiSo.getText().toString()));

        long result = db.insert("sinhvien", null, values);
        if (result > 0) {
            Toast.makeText(this, "Insert thành công", Toast.LENGTH_SHORT).show();
            query(); // Cập nhật ListView
        } else {
            Toast.makeText(this, "Insert thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void update() {
        ContentValues values = new ContentValues();
        values.put("tenlop", editTenLop.getText().toString());
        values.put("siso", Integer.parseInt(editSiSo.getText().toString()));

        String whereClause = "malop = ?";
        String[] whereArgs = {editMaLop.getText().toString()};

        int result = db.update("sinhvien", values, whereClause, whereArgs);
        if (result > 0) {
            Toast.makeText(this, "Update thành công", Toast.LENGTH_SHORT).show();
            query();
        } else {
            Toast.makeText(this, "Update thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete() {
        String whereClause = "malop = ?";
        String[] whereArgs = {editMaLop.getText().toString()};

        int result = db.delete("sinhvien", whereClause, whereArgs);
        if (result > 0) {
            Toast.makeText(this, "Delete thành công", Toast.LENGTH_SHORT).show();
            query();
        } else {
            Toast.makeText(this, "Delete thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void query() {
        list.clear();
        String[] columns = {"malop", "tenlop", "siso"};
        Cursor c = db.query("sinhvien", columns, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            do {
                String malop = c.getString(c.getColumnIndexOrThrow("malop"));
                String tenlop = c.getString(c.getColumnIndexOrThrow("tenlop"));
                int siso = c.getInt(c.getColumnIndexOrThrow("siso"));
                list.add("Mã lớp: " + malop + ", Tên lớp: " + tenlop + ", Sĩ số: " + siso);
            } while (c.moveToNext());
            c.close();
        }
        adapter.notifyDataSetChanged();
    }
}
