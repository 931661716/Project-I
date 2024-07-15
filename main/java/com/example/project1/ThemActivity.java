package com.example.project1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private RadioGroup radioGroup;
    private RadioButton checkedBtn;
//    private Boolean btnChecked = false;
    private EditText editTextDate;
    private Button datepickerBtn;
    private EditText editTextPrice;
    private TextView selectedTv;
    @Nullable
    private LoaiKhoan selectedLK;
    private Button loaiKhoanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        setTitle("Them");


        initDatePicker();
        init();
    }

    private void initDatePicker() {
//        DatePickerDialog.OnDateSetListener datePickerListener = (view, selectedYear, selectedMonth, selectedDay) -> {
//            selectedMonth+=1;
//            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
//            Date date = new Date(selectedYear, selectedMonth, selectedDay-1);
//            String dayOfWeek = simpledateformat.format(date);
//            String full_date = makeDateString(dayOfWeek,selectedDay,selectedMonth,selectedYear);
//            datepickerBtn.setText(full_date);
//        };

        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                selectedMonth+=1;
                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                java.sql.Date date = new java.sql.Date(selectedYear, selectedMonth, selectedDay-1);
                java.util.Date utilDate = new java.util.Date(date.getTime());
                String dayOfWeek = simpledateformat.format(utilDate);
                String full_date = makeDateString(dayOfWeek,selectedDay,selectedMonth,selectedYear);
                datepickerBtn.setText(full_date);
//                editTextDate.setText(full_date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = com.google.android.material.R.style.Theme_Material3_Light_Dialog_Alert;

        datePickerDialog = new DatePickerDialog(this,style,datePickerListener,year,month,day);
    }

    @NonNull
    private String makeDateString(String dayOfWeek, int day, int month, int year){
        return dayOfWeek + ", " + day + "-" + month + "-" + year;
    }

    private void init(){
        radioGroup = findViewById(R.id.radioGroup);
        datepickerBtn = findViewById(R.id.datepicker_them);
        datepickerBtn.setBackgroundColor(Color.BLACK);
        datepickerBtn.setText(getTodayDate());

        loaiKhoanBtn = findViewById(R.id.loaiKhoanBtn);
        selectedTv = findViewById(R.id.loaiKhoanSelectedTv);
    }

    @NonNull
    private String getTodayDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month+=1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return makeDateString(dayOfWeek_toString(dayOfWeek),day,month,year);
    }

    @NonNull
    private String dayOfWeek_toString(int dayOfWeek){
        if(dayOfWeek == 1)
            return "SUN";
        if(dayOfWeek == 2)
            return "MON";
        if(dayOfWeek == 3)
            return "TUE";
        if(dayOfWeek == 4)
            return "WED";
        if(dayOfWeek == 5)
            return "THU";
        if(dayOfWeek == 6)
            return "FRI";
        if(dayOfWeek == 7)
            return "SAT";
        return "OOB";
    }

    public void openDatePicker(View v){
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(Color.GREEN);
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.RED);
    }

    public void openLoaiKhoanList(View v){
        Intent i = new Intent(this, ChonLoaiKhoanActivity.class);
        startActivityForResult(i,1);

//        Intent iRcved = getIntent();
//        int passedID = iRcved.getIntExtra(LoaiKhoan.EXTRA,-1);
//        selectedLK = LoaiKhoan.getFromID(passedID);
//        if(selectedLK != null){
//            selectedTv.setText(selectedLK.getName());
//            Button b = (Button) v;
//            b.setText(selectedLK.getName());
//            loaiKhoanBtn.setText(selectedLK.getName());
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra(LoaiKhoan.EXTRA);
                loaiKhoanBtn.setText(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this,"Try agin",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void checkRadioBtn_them(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();
        checkedBtn = findViewById(radioID);
//        btnChecked = true;
    }

    public void saveKhoanThuChi(View v){
        try(DatabaseHelper databaseHelper = new DatabaseHelper(this)){

            editTextDate = findViewById(R.id.editTextDate_Them);
            editTextPrice = findViewById(R.id.editTextNumber);


            KhoanThuChi khoanThuChi = new KhoanThuChi(KhoanThuChi.StringToDate(datepickerBtn.getText().toString()),checkedBtn.getText().toString(),loaiKhoanBtn.getText().toString(),Integer.parseInt(editTextPrice.getText().toString()));

            databaseHelper.insertTable(khoanThuChi);

        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
        }
    }

}