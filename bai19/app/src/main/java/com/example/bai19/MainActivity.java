package com.example.bai19;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnParse;
    ListView listView;
    ArrayList<String> employeeList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParse = findViewById(R.id.btnParse);
        listView = findViewById(R.id.listView);
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        listView.setAdapter(adapter);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseXML();
            }
        });
    }

    private void parseXML() {
        try {
            InputStream inputStream = getAssets().open("employee.xml");
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(inputStream, null);

            String id = "", title = "", name = "", phone = "";
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();

                        if ("employee".equals(tagName)) {
                            id = parser.getAttributeValue(null, "id");
                            title = parser.getAttributeValue(null, "title");
                        } else if ("name".equals(tagName)) {
                            name = parser.nextText();
                        } else if ("phone".equals(tagName)) {
                            phone = parser.nextText();
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if ("employee".equals(tagName)) {
                            employeeList.add(id + " " + title + " - " + name + " - " + phone);
                            adapter.notifyDataSetChanged();
                        }
                        break;
                }
                eventType = parser.next();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
