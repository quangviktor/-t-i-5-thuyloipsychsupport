package com.example.lviewandspiner2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //Khai báo các biến
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapater;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;

    private static final String PREFS_NAME = "MyNotesPrefs";
    private static final String NOTES_KEY = "notes_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtminute);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listview1);
        txtdate = findViewById(R.id.txtdate);

        // Khởi tạo và tải dữ liệu từ SharedPreferences
        arraywork = loadNotes();
        // Sắp xếp dữ liệu theo thứ tự ngược lại để mục mới nhất ở trên cùng
        Collections.reverse(arraywork);

        // Khai báo ArrayAdapter
        arrAdapater = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);
        // Gán Adapter cho ListView
        lv.setAdapter(arrAdapater);

        // Lấy ngày giờ hệ thống và hiển thị lên TextView
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm Nay: " + simpleDateFormat.format(currentDate));

        // Sự kiện khi click vào Button "Add Work"
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtwork.getText().toString().equals("") || edth.getText().toString().equals("")
                        || edtm.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", null);
                    builder.show();
                } else {
                    String str = edtwork.getText().toString() + " - " +
                            edth.getText().toString() + ":" + edtm.getText().toString();

                    // Thêm dữ liệu vào đầu danh sách
                    arraywork.add(0, str);

                    arrAdapater.notifyDataSetChanged();
                    saveNotes(arraywork); // Lưu dữ liệu sau khi thêm

                    edth.setText("");
                    edtm.setText("");
                    edtwork.setText("");
                }
            }
        });

        // Sự kiện khi click vào một dòng trên ListView để xóa
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // Hiển thị hộp thoại xác nhận xóa
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xóa công việc");
                builder.setMessage("Bạn có chắc chắn muốn xóa công việc này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa mục khỏi ArrayList
                        arraywork.remove(position);
                        // Cập nhật lại Adapter
                        arrAdapater.notifyDataSetChanged();
                        // Lưu lại danh sách sau khi xóa
                        saveNotes(arraywork);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    // Phương thức để lưu dữ liệu vào SharedPreferences
    private void saveNotes(ArrayList<String> notes) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<>(notes);
        editor.putStringSet(NOTES_KEY, set);
        editor.apply();
    }

    // Phương thức để tải dữ liệu từ SharedPreferences
    private ArrayList<String> loadNotes() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet(NOTES_KEY, new HashSet<String>());
        return new ArrayList<>(set);
    }
}