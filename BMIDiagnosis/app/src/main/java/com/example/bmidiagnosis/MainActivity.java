package com.example.bmidiagnosis;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // Đã sửa tên biến để khớp với ID trong XML của bạn
    EditText txtName, txtHeight, txtWeight, txtBMI, txtChuanD;
    Button btnBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Khởi tạo các thành phần UI với ID chính xác từ XML của bạn
        txtName = findViewById(R.id.txtName);
        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        txtBMI = findViewById(R.id.txtBMI);
        txtChuanD = findViewById(R.id.txtChuanD);
        btnBMI = findViewById(R.id.btnCal);

        btnBMI.setOnClickListener(v -> {
            // Lấy văn bản từ các trường nhập liệu
            String heightStr = txtHeight.getText().toString();
            String weightStr = txtWeight.getText().toString();

            // Chuyển đổi văn bản sang kiểu double
            // Lưu ý: Nếu người dùng nhập giá trị không phải số hoặc để trống,
            // ứng dụng có thể gặp lỗi NumberFormatException và crash.
            double H = Double.parseDouble(heightStr);
            double W = Double.parseDouble(weightStr);

            // Tính toán BMI
            double BMI = W / Math.pow(H, 2);

            String chandoan = "";
            // Logic chẩn đoán BMI
            if (BMI <= 18.5) {
                chandoan = "Bạn gầy";
            } else if (BMI <= 24.9) {
                chandoan = "Bạn bình thường";
            } else if (BMI <= 29.9) {
                chandoan = "Bạn béo phì độ 1";
            } else if (BMI <= 34.9) {
                chandoan = "Bạn béo phì độ 2";
            } else {
                chandoan = "Bạn béo phì độ 3";
            }

            // Định dạng BMI với một chữ số thập phân
            DecimalFormat dcf = new DecimalFormat("#.0");

            // Hiển thị kết quả BMI và chẩn đoán
            txtBMI.setText(dcf.format(BMI));
            txtChuanD.setText(chandoan);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}