package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TimKiemActivity extends AppCompatActivity {

    private ListView searchListView;
    private ArrayList<KhoanThuChi> searchArrayList = KhoanThuChi.khoanThuChiArrayList;
    private EditText date;
    private EditText loaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        setTitle("Tim Kiem");
    }

    public void Search(View v){
        try ( DatabaseHelper databaseHelper = new DatabaseHelper(this)){
            searchListView = findViewById(R.id.searchListView);

            date = findViewById(R.id.dateSearchTxt);
            loaiKhoan = findViewById(R.id.loaiKhoanSearchTxt);

            if(!date.toString().equals("@string/DateFormatText") && date != null && !loaiKhoan.toString().equals("@string/loaiKhoanTextview") && loaiKhoan != null){
                searchArrayList = databaseHelper.getTableData_thuchi("SELECT * FROM "+DatabaseHelper.THUCHI_TABLE_NAME+
                        "WHERE "+DatabaseHelper.THUCHI_COL2+" = '"+date.toString()+"' "+
                        "AND "+DatabaseHelper.THUCHI_COL4+" = '"+loaiKhoan.toString()+"'");

            } else {
                assert date != null;
                if (!date.toString().equals("@string/DateFormatText") && date != null) {
                    searchArrayList = databaseHelper.getTableData_thuchi("SELECT * FROM "+DatabaseHelper.THUCHI_TABLE_NAME+
                            "WHERE "+DatabaseHelper.THUCHI_COL2+" = '"+ date +"' ");

                } else if (!loaiKhoan.toString().equals("@string/loaiKhoanTextview") && date != null) {
                    searchArrayList = databaseHelper.getTableData_thuchi("SELECT * FROM "+DatabaseHelper.THUCHI_TABLE_NAME+
                            "WHERE "+DatabaseHelper.THUCHI_COL4+" = '"+loaiKhoan.toString()+"'");

                }
            }

            KhoanThuChiAdapter khoanThuChiAdapter = new KhoanThuChiAdapter(getApplicationContext(),R.layout.line_thuchi,searchArrayList);
            searchListView.setAdapter(khoanThuChiAdapter);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }
}