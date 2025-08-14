package com.example.bai22; // Đảm bảo package name khớp với project của bạn

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList = new ArrayList<>(); // Sử dụng để lưu dữ liệu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        // Khởi động AsyncTask để tải dữ liệu XML từ URL
        new FetchXMLDataTask().execute("https://api.androidhive.info/gism/fomat-xml.xml");
    }

    // Lớp AsyncTask để thực hiện tác vụ mạng
    private class FetchXMLDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String xmlResponse = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                xmlResponse = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return xmlResponse;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    // Sử dụng SAXParser để phân tích chuỗi XML
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser saxParser = factory.newSAXParser();

                    // Khởi tạo XML Handler
                    MyXMLHandler handler = new MyXMLHandler(dataList);
                    saxParser.parse(new InputSource(new StringReader(result)), handler);

                    // Cập nhật ListView
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Lỗi phân tích XML", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Không thể tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Lớp XML Handler tùy chỉnh để xử lý các thẻ XML
    private class MyXMLHandler extends DefaultHandler {
        private List<String> dataList;
        private String elementValue;

        public MyXMLHandler(List<String> list) {
            this.dataList = list;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            elementValue = new String(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            // Lấy nội dung của thẻ <title> để thêm vào danh sách
            if (qName.equalsIgnoreCase("title")) {
                dataList.add(elementValue);
            }
        }
    }
}