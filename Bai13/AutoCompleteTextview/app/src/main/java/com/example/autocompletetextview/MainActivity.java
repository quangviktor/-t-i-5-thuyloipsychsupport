package com.example.autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.autocompletetextview.R;

public class MainActivity extends AppCompatActivity {
    //Khai báo 2 CompleteTextView
    TextView selection;
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;

    //Khởi tạo mảng tạm để Test
    String arr[] = {"hà nội", "huế", "sài gòn",
            "hà giang", "hội an", "kiên giang",
            "lâm đồng", "long khánh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = findViewById(R.id.selection);

        //lấy đối tượng AutoCompleteTextView ra
        singleComplete = findViewById(R.id.editauto);
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        );
        //Thiết lập ArrayAdapter
        singleComplete.setAdapter(myadapter);

        //Lấy đối tượng MultiAutoCompleteTextView ra
        multiComplete = findViewById(R.id.multiAutoCompleteTextView1);
        multiComplete.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        ));
        //Đối với MultiAutoCompleteTextView bắt buộc phải gọi dòng lệnh này
        multiComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        singleComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                selection.setText(singleComplete.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}