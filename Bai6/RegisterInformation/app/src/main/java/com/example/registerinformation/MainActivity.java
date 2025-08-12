package com.example.registerinformation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registerinformation.R;

public class MainActivity extends AppCompatActivity {

    EditText editHoten, editCMND, editBosung;
    CheckBox chkdocbao, chkdocsach, chkdoccoding;
    Button btnguitt;
    RadioGroup radioGroup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editHoten = findViewById(R.id.editHoten);
        editCMND = findViewById(R.id.editCMND);
        editBosung = findViewById(R.id.editBosung);
        chkdocbao = findViewById(R.id.chkdocbao);
        chkdoccoding = findViewById(R.id.chkdoccoding);
        chkdocsach = findViewById(R.id.chkdocsach);
        btnguitt = findViewById(R.id.btnguitt);
        radioGroup1 = findViewById(R.id.radioGroup1);

        btnguitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doShowInformation();
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Question");
                b.setMessage("Are you sure you want to exit?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
            }
        });
    }

    public void doShowInformation() {
        String ten = editHoten.getText().toString().trim();
        if (ten.length() < 3) {
            editHoten.requestFocus();
            editHoten.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String cmnd = editCMND.getText().toString().trim();
        if (cmnd.length() != 9) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String bang = "";
        int id = radioGroup1.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        bang = rad.getText().toString();

        String sothich = "";
        if (chkdocbao.isChecked()) {
            sothich += chkdocbao.getText().toString() + "\n";
        }
        if (chkdocsach.isChecked()) {
            sothich += chkdocsach.getText().toString() + "\n";
        }
        if (chkdoccoding.isChecked()) {
            sothich += chkdoccoding.getText().toString() + "\n";
        }

        String bosung = editBosung.getText().toString();

        // Tạo nội dung thông báo
        String msg = "Họ tên: " + ten + "\n";
        msg += "CMND: " + cmnd + "\n";
        msg += "Bằng cấp: " + bang + "\n";
        msg += "Sở thích:\n" + sothich;
        msg += "-----------------------------------\n";
        msg += "Thông tin bổ sung:\n";
        msg += bosung + "\n";
        msg += "-----------------------------------";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setMessage(msg);
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}