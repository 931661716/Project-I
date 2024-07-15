package com.example.project1;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

public class ThongKeActivity extends AppCompatActivity {

    int thu_thang;
    int chi_thang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setTitle("Thong Ke");
    }

    public void thongKe(View v){
        try(DatabaseHelper databaseHelper = new DatabaseHelper(this)) {

            //SELECT strftime('%d', trxDateTime) as valDay,
            //SUM(trxAmount) as valTotalDay
            //FROM trx_log
            //WHERE
            //strftime('%Y', trxDateTime)='2014'
            //AND strftime('%m', trxDateTime) ='02'
            //GROUP BY valDay
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                thu_thang = databaseHelper.sum("SELECT strftime('%d', " + DatabaseHelper.THUCHI_COL2 + ") as val," +
                        "SUM(" + DatabaseHelper.THUCHI_COL5 + ") " +
                        "FROM " + DatabaseHelper.THUCHI_TABLE_NAME +
                        " WHERE " + DatabaseHelper.THUCHI_COL3 + " = 'Thu' " +
                        "AND strftime('%Y', " + DatabaseHelper.THUCHI_COL2 + ")='" + LocalDate.now().getYear() + "' " +
                        "AND strftime('%m', " + DatabaseHelper.THUCHI_COL2 + ")='" + LocalDate.now().getMonthValue() + "' " +
                        "GROUP BY val");

                chi_thang = databaseHelper.sum("SELECT strftime('%d', " + DatabaseHelper.THUCHI_COL2 + ") as val," +
                        "SUM(" + DatabaseHelper.THUCHI_COL5 + ") " +
                        "FROM " + DatabaseHelper.THUCHI_TABLE_NAME +
                        " WHERE " + DatabaseHelper.THUCHI_COL3 + " = 'Chi' " +
                        "AND strftime('%Y', " + DatabaseHelper.THUCHI_COL2 + ")='" + LocalDate.now().getYear() + "' " +
                        "AND strftime('%m', " + DatabaseHelper.THUCHI_COL2 + ")='" + LocalDate.now().getMonthValue() + "' " +
                        "GROUP BY val");
            }

            TextView tv1 = findViewById(R.id.statisticTv1);
            tv1.setText(thu_thang);

            TextView tv2 = findViewById(R.id.statisticTv2);
            tv2.setText(chi_thang);

            if (chi_thang > KhoanThuChi.nguongChi_thang) {
                Toast.makeText(this, "Khoan chi thang vuot nguong " + KhoanThuChi.nguongChi_thang, Toast.LENGTH_LONG).show();
            }
        }

    }
}