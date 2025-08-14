package com.example.bai14;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends TabActivity {
    EditText edita, editb;
    Button btntong;
    TextView txtkq;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addControl() {
        // TabHost
        TabHost tab = getTabHost();
        TabHost.TabSpec spec1 = tab.newTabSpec("t1");
        spec1.setIndicator("Tab 1");
        spec1.setContent(R.id.tab1);
        tab.addTab(spec1);

        TabHost.TabSpec spec2 = tab.newTabSpec("t2");
        spec2.setIndicator("Tab 2");
        spec2.setContent(R.id.tab2);
        tab.addTab(spec2);

        // Các controls khác
        edita = (EditText) findViewById(R.id.edita);
        editb = (EditText) findViewById(R.id.editb);
        btntong = (Button) findViewById(R.id.btntong);
        txtkq = (TextView) findViewById(R.id.txtkq);

        lv1 = (ListView) findViewById(R.id.lv1);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv1.setAdapter(adapter);
    }

    private void addEvent() {
        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(edita.getText().toString());
                    int b = Integer.parseInt(editb.getText().toString());
                    int sum = a + b;
                    String result = a + " + " + b + " = " + sum;

                    txtkq.setText("Kết quả: " + sum);
                    list.add(result);
                    adapter.notifyDataSetChanged();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
