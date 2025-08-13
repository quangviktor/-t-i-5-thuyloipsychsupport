package com.example.bai20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {

    // Khai báo các View trong layout
    private ProgressBar progressBar;
    private TextView textView;
    private Button startButton;

    // Khai báo đối tượng AsyncTask
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Đảm bảo tên layout đúng

        // Ánh xạ các View từ layout
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);

        // Thiết lập sự kiện click cho nút "Start"
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một đối tượng AsyncTask mới mỗi khi bấm nút
                // và thực thi nó
                myAsyncTask = new MyAsyncTask(MainActivity.this);
                myAsyncTask.execute();
            }
        });
    }

    // Lớp MyAsyncTask
    // Lớp này nên là một lớp con nội bộ của MainActivity để dễ dàng truy cập các View
    public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        // Biến lưu trữ ngữ cảnh (context) của MainActivity
        private MainActivity context;

        public MyAsyncTask(MainActivity context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Hàm này chạy trên UI thread trước khi doInBackground được gọi
            Toast.makeText(context, "onPreExecute!", Toast.LENGTH_LONG).show();
            // Bạn có thể làm các thao tác chuẩn bị giao diện ở đây
            context.progressBar.setProgress(0);
            context.textView.setText("0%");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Hàm này chạy trên luồng nền (background thread)
            // Tuyệt đối không cập nhật UI ở đây
            for (int i = 0; i <= 100; i++) {
                SystemClock.sleep(100); // Giả lập tác vụ nặng, mỗi lần lặp 100ms
                publishProgress(i); // Gọi onProgressUpdate để cập nhật UI
            }
            return null; // Trả về null vì kiểu dữ liệu là Void
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // Hàm này chạy trên UI thread, được gọi bởi publishProgress
            // Cập nhật giao diện ở đây
            context.progressBar.setProgress(values[0]);
            context.textView.setText(values[0] + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Hàm này chạy trên UI thread sau khi doInBackground hoàn tất
            Toast.makeText(context, "Update xong roi da!", Toast.LENGTH_LONG).show();
        }
    }
}
