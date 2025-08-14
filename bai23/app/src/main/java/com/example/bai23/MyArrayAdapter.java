package com.example.bai23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

// Sửa từ ArrayAdapter<List> thành ArrayAdapter<NewsItem>
public class MyArrayAdapter extends ArrayAdapter<NewsItem> {

    public MyArrayAdapter(Context context, int resource, List<NewsItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_listview, null);
        }

        // Lấy đối tượng NewsItem tại vị trí hiện tại
        NewsItem p = getItem(position);

        if (p != null) {
            TextView title = v.findViewById(R.id.txtTitle);
            TextView pubDate = v.findViewById(R.id.txtPubDate);
            ImageView img = v.findViewById(R.id.imgNews);

            if (title != null) {
                // Sử dụng getters để lấy dữ liệu từ NewsItem
                title.setText(p.getTitle());
            }
            if (pubDate != null) {
                pubDate.setText(p.getPubDate());
            }
            if (img != null && p.getImage() != null && !p.getImage().isEmpty()) {
                Picasso.get().load(p.getImage()).into(img);
            }
        }

        return v;
    }
}