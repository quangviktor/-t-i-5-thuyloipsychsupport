package com.example.bi18;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.text.*;

import java.io.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase database = null;
    public static final String DATABASE_NAME = "arirang.sqlite";

    EditText edttim;
    ImageButton btnxoa;
    ListView lv1, lv2, lv3;
    ArrayList<Item> list1, list2, list3;
    MyArrayAdapter adapter1, adapter2, adapter3;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processCopyDatabase();                 // copy DB từ assets nếu chưa có
        database = openDatabase();             // mở DB để dùng

        addControl();
        addEvents();
        addDanhSach();
        addYeuThich();
    }

    private void addControl() {
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("t1");
        spec1.setIndicator("Tìm kiếm");
        spec1.setContent(R.id.tab1);
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("t2");
        spec2.setIndicator("Danh sách");
        spec2.setContent(R.id.tab2);
        tabHost.addTab(spec2);

        TabHost.TabSpec spec3 = tabHost.newTabSpec("t3");
        spec3.setIndicator("Yêu thích");
        spec3.setContent(R.id.tab3);
        tabHost.addTab(spec3);

        edttim = findViewById(R.id.edttim);
        btnxoa = findViewById(R.id.btnxoa);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        adapter1 = new MyArrayAdapter(this, R.layout.listitem, list1);
        adapter2 = new MyArrayAdapter(this, R.layout.listitem, list2);
        adapter3 = new MyArrayAdapter(this, R.layout.listitem, list3);

        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
        lv3.setAdapter(adapter3);
    }

    private void addEvents() {
        btnxoa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                edttim.setText("");
            }
        });

        edttim.addTextChangedListener(new TextWatcher(){
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { getData(); }
            @Override public void afterTextChanged(Editable s) { }
        });
    }

    private void getData() {
        String key = edttim.getText().toString().trim();
        list1.clear();
        Cursor c;
        if(key.length() == 0) {
            c = database.rawQuery("SELECT * FROM ArirangSongList", null);
        } else {
            String s = "%" + key + "%";
            c = database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH LIKE ? OR TENBH LIKE ?", new String[]{s, s});
        }
        if(c != null && c.moveToFirst()) {
            do {
                list1.add(new Item(c.getString(1), c.getString(2), c.getInt(6)));
            } while(c.moveToNext());
            c.close();
        }
        adapter1.notifyDataSetChanged();
    }

    private void addDanhSach() {
        list2.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList", null);
        if(c != null && c.moveToFirst()) {
            do {
                list2.add(new Item(c.getString(1), c.getString(2), c.getInt(6)));
            } while(c.moveToNext());
            c.close();
        }
        adapter2.notifyDataSetChanged();
    }

    private void addYeuThich() {
        list3.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList WHERE YEUTHICH = 1", null);
        if(c != null && c.moveToFirst()) {
            do {
                list3.add(new Item(c.getString(1), c.getString(2), c.getInt(6)));
            } while(c.moveToNext());
            c.close();
        }
        adapter3.notifyDataSetChanged();
    }

    // Copy DB từ assets (nếu chưa tồn tại)
    private void processCopyDatabase() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            try {
                InputStream is = getAssets().open(DATABASE_NAME);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private SQLiteDatabase openDatabase() {
        String dbPath = getDatabasePath(DATABASE_NAME).getPath();
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
