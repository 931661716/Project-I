package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView homeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        try ( DatabaseHelper databaseHelper = new DatabaseHelper(this)){
            homeListView = findViewById(R.id.listView_Home);

            KhoanThuChi.khoanThuChiArrayList = databaseHelper.getTableData_thuchi("SELECT * FROM " + DatabaseHelper.THUCHI_TABLE_NAME);
            KhoanThuChiAdapter khoanThuChiAdapter = new KhoanThuChiAdapter(getApplicationContext(),R.layout.line_thuchi,KhoanThuChi.khoanThuChiArrayList);
            homeListView.setAdapter(khoanThuChiAdapter);

//            LoaiKhoan loaiKhoan = new LoaiKhoan("Thu", "Luong");
//
//            databaseHelper.insertTable(loaiKhoan);
        }
        catch (Exception e){ }




//        databaseHelper = new DatabaseHelper(this);
//
//        databaseHelper.query("CREATE TABLE IF NOT EXISTS ThuChi(No INTEGER PRIMARY KEY AUTOINCREMENT, Ngay DATE, Thu/Chi VARCHAR(5), LoaiKhoan VARCHAR(25), GiaTri INTEGER)");
//
//
//        Cursor dataThuChi = databaseHelper.getData("SELECT * FROM ThuChi");
//        showDatabase(dataThuChi);

    }

//    public void insertDatabase(){
//        databaseHelper.query("INSERT");
//    }
//
//    public void showDatabase(Cursor selectedData){
//        while (selectedData.moveToNext()){
//
//        }
//    }

    public void launchActivity_TimKiem(View v){
        Intent i = new Intent(this, TimKiemActivity.class);


        startActivity(i);
    }

    public void launchActivity_Them(View v){
        Intent i = new Intent(this, ThemActivity.class);

        startActivity(i);
    }

    public void launchActivity_ThongKe(View v){
        Intent i = new Intent(this, ThongKeActivity.class);

        startActivity(i);
    }

    public void launchActivity_ThemLoaiKhoan(View v){
        Intent i = new Intent(this, ThemLoaiKhoanActivity.class);


        startActivity(i);
    }

    public void launchActivity_DatNguongChi(View v){
        Intent i = new Intent(this, DatNguongChiActivity.class);


        startActivity(i);
    }
}