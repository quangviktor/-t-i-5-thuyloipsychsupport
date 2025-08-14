package com.example.bai23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    // Thay đổi từ ArrayList<List> thành ArrayList<NewsItem>
    ArrayList<NewsItem> arrayList;
    MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();

        new ReadRSS().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");
    }

    private class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            XMLParser parser = new XMLParser();
            return parser.getXmlFromUrl(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = factory.newPullParser();
                xmlPullParser.setInput(new StringReader(s));

                int eventType = xmlPullParser.getEventType();
                String title = "", link = "", pubDate = "", description = "", image = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tagName = xmlPullParser.getName();

                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if ("title".equalsIgnoreCase(tagName)) {
                                title = xmlPullParser.nextText();
                            } else if ("link".equalsIgnoreCase(tagName)) {
                                link = xmlPullParser.nextText();
                            } else if ("pubDate".equalsIgnoreCase(tagName)) {
                                pubDate = xmlPullParser.nextText();
                            } else if ("description".equalsIgnoreCase(tagName)) {
                                description = xmlPullParser.nextText();

                                int imgStart = description.indexOf("src=");
                                if (imgStart != -1) {
                                    int startQuote = description.indexOf("\"", imgStart) + 1;
                                    int endQuote = description.indexOf("\"", startQuote);
                                    image = description.substring(startQuote, endQuote);
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if ("item".equalsIgnoreCase(tagName)) {
                                // Thay đổi từ new List(...) thành new NewsItem(...)
                                arrayList.add(new NewsItem(title, description, link, pubDate, image));
                            }
                            break;
                    }
                    eventType = xmlPullParser.next();
                }

                // Cập nhật MyArrayAdapter để sử dụng NewsItem
                adapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview, arrayList);
                listView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}