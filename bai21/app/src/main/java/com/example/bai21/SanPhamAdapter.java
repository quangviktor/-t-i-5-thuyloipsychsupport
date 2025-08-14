package com.example.bai21; // Đảm bảo package name của bạn đúng

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// Chắc chắn rằng SanPhamAdapter kế thừa từ ArrayAdapter<SanPham>
public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    // Constructor phải gọi constructor của lớp cha
    public SanPhamAdapter(Context context, ArrayList<SanPham> sanphams) {
        super(context, 0, sanphams);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Lấy đối tượng SanPham tại vị trí này
        SanPham sanPham = getItem(position);

        // Kiểm tra xem View đã được tái sử dụng chưa
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sanpham, parent, false);
        }

        // Ánh xạ các TextView từ layout item
        TextView tvMaSP = convertView.findViewById(R.id.textViewMaSP);
        TextView tvTenSP = convertView.findViewById(R.id.textViewTenSP);
        // ... (Nếu bạn có các TextView khác)

        // Đặt dữ liệu từ đối tượng SanPham vào các TextView
        tvMaSP.setText(sanPham.getMaSP());
        tvTenSP.setText(sanPham.getTenSP());
        // ...

        // Trả về View đã hoàn chỉnh
        return convertView;
    }
}