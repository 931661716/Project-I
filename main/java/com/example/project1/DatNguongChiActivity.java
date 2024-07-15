package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatNguongChiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_nguong_chi);
        setTitle("Dat nguong chi");


    }

    public void saveNguongChi(View v){
        KhoanThuChi.nguongChi_ngay = Integer.parseInt(findViewById(R.id.datNguongChi_Ngay_editText).toString());
        KhoanThuChi.nguongChi_thang = Integer.parseInt(findViewById(R.id.datNguongChi_Thang_editText).toString());
        Toast.makeText(this,"Thanh cong",Toast.LENGTH_SHORT).show();
    }

}
