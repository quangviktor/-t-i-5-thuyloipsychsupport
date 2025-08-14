package com.example.bai14;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class myArrayAdapter extends ArrayAdapter<Item> {
    private Context context;
    private int layoutId;
    private ArrayList<Item> myArray;

    public myArrayAdapter(Context context, int layoutId, ArrayList<Item> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(layoutId, parent, false);

        TextView txtMaso = (TextView) row.findViewById(R.id.txtMaSo);
        TextView txtTieude = (TextView) row.findViewById(R.id.txtTieuDe);
        ImageView btnLike = (ImageView) row.findViewById(R.id.btnLike);

        Item myItem = myArray.get(position);

        txtMaso.setText(myItem.getMaso());
        txtTieude.setText(myItem.getTieude());

        if (myItem.getThich() == 1) {
            btnLike.setImageResource(R.drawable.like);
        } else {
            btnLike.setImageResource(R.drawable.unlike);
        }

        // Xử lý sự kiện click trên nút like
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đảo trạng thái like
                if (myItem.getThich() == 1) {
                    myItem.setThich(0);
                } else {
                    myItem.setThich(1);
                }
                // Cập nhật lại giao diện
                notifyDataSetChanged();
            }
        });

        return row;
    }
}
