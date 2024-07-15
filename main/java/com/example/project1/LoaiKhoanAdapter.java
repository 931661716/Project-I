package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LoaiKhoanAdapter extends ArrayAdapter<LoaiKhoan> {
    public LoaiKhoanAdapter(@NonNull Context context, List<LoaiKhoan> list) {
        super(context, 0,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LoaiKhoan loaiKhoan = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_loaikhoan,parent,false);

            TextView content = convertView.findViewById(R.id.lineContent);

            content.setText(loaiKhoan.getName());
        }


        return convertView;
    }
}
