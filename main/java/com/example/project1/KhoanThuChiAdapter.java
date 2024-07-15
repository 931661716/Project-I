package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class KhoanThuChiAdapter extends BaseAdapter{

    private Context context;
    private  int layout;
    private List<KhoanThuChi> list_thuchi;

    public KhoanThuChiAdapter(Context context, int layout, List<KhoanThuChi> list_thuchi) {
        this.context = context;
        this.layout = layout;
        this.list_thuchi = list_thuchi;
    }

//    public KhoanThuChiAdapter(Context applicationContext, ArrayList<KhoanThuChi> khoanThuChiArrayList) {
//        this.context = context;
//        this.list_thuchi = list_thuchi;
//    }

    @Override
    public int getCount() {
        return list_thuchi.size();
    }

    @Nullable
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    private class ViewHolder{
//
//    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView noTv = convertView.findViewById(R.id.noTv);
        TextView ngayTv = convertView.findViewById(R.id.ngayTv);
        TextView thu_or_chiTv = convertView.findViewById(R.id.thu_or_chiTv);
        TextView loaiKhoanTv = convertView.findViewById(R.id.loaiKhoanTv);
        TextView giaTriTv = convertView.findViewById(R.id.giaTriTv);

        noTv.setText(list_thuchi.get(position).getID());
        ngayTv.setText(list_thuchi.get(position).getNgay_String());
        thu_or_chiTv.setText(list_thuchi.get(position).getThu_or_chi());
        loaiKhoanTv.setText(list_thuchi.get(position).getLoaiKhoan());
        giaTriTv.setText(list_thuchi.get(position).getGiaTri());

        return convertView;
    }
}
