package com.example.bi18;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    private Activity context;
    private int layoutId;
    private ArrayList<Item> myArray;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Item> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, null);
        }

        TextView txtMaso = convertView.findViewById(R.id.txtmaso);
        TextView txtTieude = convertView.findViewById(R.id.txttieude);
        final ImageView btnUn = convertView.findViewById(R.id.btnunlike);
        final ImageView btnLike = convertView.findViewById(R.id.btnlike);

        final Item it = myArray.get(position);
        txtMaso.setText(it.getMaso());
        txtTieude.setText(it.getTieude());

        if (it.getThich() != null && it.getThich() == 1) {
            btnLike.setVisibility(View.VISIBLE);
            btnUn.setVisibility(View.INVISIBLE);
        } else {
            btnLike.setVisibility(View.INVISIBLE);
            btnUn.setVisibility(View.VISIBLE);
        }

        btnUn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                SQLiteDatabase db = MainActivity.database;
                db.update("ArirangSongList", values, "MABH = ?", new String[]{ it.getMaso() });
                it.setThich(1);
                notifyDataSetChanged();
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                SQLiteDatabase db = MainActivity.database;
                db.update("ArirangSongList", values, "MABH = ?", new String[]{ it.getMaso() });
                it.setThich(0);
                notifyDataSetChanged();
            }
        });

        // Click dòng mở activity chi tiết
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, ActivitySub.class);
                intent.putExtra("maso", it.getMaso());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
