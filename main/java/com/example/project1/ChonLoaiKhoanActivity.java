package com.example.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ChonLoaiKhoanActivity extends AppCompatActivity {

    private ListView loaiKhoanListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_loai_khoan);
        setTitle("Chon loai khoan");

        loaiKhoanListView = findViewById(R.id.listView_chon_loai_khoan);

        try(DatabaseHelper databaseHelper = new DatabaseHelper(this)){
            LoaiKhoan.loaiKhoanArrayList = databaseHelper.getTableData_loaikhoan("SELECT * FROM "+DatabaseHelper.LOAIKHOAN_TABLE_NAME);
        }

        LoaiKhoanAdapter loaiKhoanAdapter = new LoaiKhoanAdapter(getApplicationContext(),LoaiKhoan.loaiKhoanArrayList);
        loaiKhoanListView.setAdapter(loaiKhoanAdapter);

        setOnClickListener();


    }

    private void setOnClickListener(){
        loaiKhoanListView.setOnItemClickListener((adapterView, view, position, l) -> {
            LoaiKhoan selected = (LoaiKhoan) loaiKhoanListView.getItemAtPosition(position);
            Intent i = new Intent(getApplicationContext(), ThemActivity.class);
            i.putExtra(LoaiKhoan.EXTRA, selected.getName());
            setResult(Activity.RESULT_OK,i);
            finish();
//            startActivity(i);
        });
    }
}
