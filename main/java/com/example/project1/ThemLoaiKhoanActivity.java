package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThemLoaiKhoanActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton checkedBtn;
    @NonNull
    private Boolean btnChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_khoan);
        setTitle("Them loai khoan");

        radioGroup = findViewById(R.id.radioGroup_sub);

    }

    public void addLoaiKhoan_DB(View v){
        if (btnChecked){
            try(DatabaseHelper databaseHelper = new DatabaseHelper(this)){
                EditText editText = findViewById(R.id.editTextThemLoaiKhoan);

                LoaiKhoan loaiKhoan = new LoaiKhoan(checkedBtn.getText().toString(), editText.getText().toString());

                databaseHelper.insertTable(loaiKhoan);

            }
            catch (Exception e){
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this,"Ban chua chon loai khoan",Toast.LENGTH_SHORT).show();
        }
    }

    public void checkRadioBtn_them_loai_khoan(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();
        checkedBtn = findViewById(radioID);
        btnChecked = true;
    }

}
