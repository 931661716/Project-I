package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int version = 1;

    public static final String DATABASE_NAME = "QuanLyThuChi.db";
    public static final String THUCHI_TABLE_NAME = "ThuChi";
    public static final String LOAIKHOAN_TABLE_NAME = "LoaiKhoan";

    public static final String THUCHI_COL1 = "ID";
    public static final String THUCHI_COL2 = "Ngay";
    public static final String THUCHI_COL3 = "Thu_or_Chi";
    public static final String THUCHI_COL4 = "LoaiKhoan";
    public static final String THUCHI_COL5 = "GiaTri";

    public static final String LOAIKHOAN_COL1 = "ID";
    public static final String LOAIKHOAN_COL2 = "Type";
    public static final String LOAIKHOAN_COL3 = "Name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

//    public void query(String sql){
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql);
//    }
//
//    public Cursor getData(String sql){
//        SQLiteDatabase database = getReadableDatabase();
//        return database.rawQuery(sql,null);
//    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ THUCHI_TABLE_NAME +
                    "("+ THUCHI_COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    THUCHI_COL2 +" DATE, " +
                    THUCHI_COL3 +" VARCHAR(5), " +
                    THUCHI_COL4 +" VARCHAR(25), " +
                    THUCHI_COL5 +" INTEGER)");

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ LOAIKHOAN_TABLE_NAME +"("+
                    LOAIKHOAN_COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOAIKHOAN_COL2 +" VARCHAR(5), " +
                    LOAIKHOAN_COL3 +" VARCHAR(25))");



        }
        catch (Exception e){
//            System.out.println("Table creation error");
        }
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ THUCHI_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ LOAIKHOAN_TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public boolean insertTable(@NonNull KhoanThuChi khoanThuChi){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(THUCHI_COL2,khoanThuChi.getNgay_String());
        contentValues.put(THUCHI_COL3,khoanThuChi.getThu_or_chi());
        contentValues.put(THUCHI_COL4,khoanThuChi.getLoaiKhoan());
        contentValues.put(THUCHI_COL5,khoanThuChi.getGiaTri());

        long result = sqLiteDatabase.insert(THUCHI_TABLE_NAME,null,contentValues);

//        sqLiteDatabase.close();
        return result != -1;
    }

    public boolean insertTable(@NonNull LoaiKhoan loaiKhoan){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(LOAIKHOAN_COL2,loaiKhoan.getType());
        contentValues.put(LOAIKHOAN_COL3,loaiKhoan.getName());

        long result = sqLiteDatabase.insert(LOAIKHOAN_TABLE_NAME,null,contentValues);

//        sqLiteDatabase.close();
        return result != -1;
    }


    @NonNull
    public ArrayList<KhoanThuChi> getTableData_thuchi(String exeQuery){
        ArrayList<KhoanThuChi> selectedList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(exeQuery,null);

        if(cursor.moveToFirst()){
            do{
                KhoanThuChi khoanThuChi = new KhoanThuChi();
                khoanThuChi.setID(Integer.parseInt(cursor.getString(0)));
                khoanThuChi.setNgay(Date.valueOf(cursor.getString(1)));
                khoanThuChi.setThu_or_chi(cursor.getString(2));
                khoanThuChi.setLoaiKhoan(cursor.getString(3));
                khoanThuChi.setGiaTri(Integer.parseInt(cursor.getString(4)));

                selectedList.add(khoanThuChi);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return  selectedList;
    }

    @NonNull
    public ArrayList<LoaiKhoan> getTableData_loaikhoan(String exeQuery){
        ArrayList<LoaiKhoan> selectedList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(exeQuery,null);

        if(cursor.moveToFirst()){
            do{
                LoaiKhoan loaiKhoan = new LoaiKhoan();
                loaiKhoan.setID(Integer.parseInt(cursor.getString(0)));
                loaiKhoan.setType(cursor.getString(1));
                loaiKhoan.setName(cursor.getString(2));

                selectedList.add(loaiKhoan);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return selectedList;
    }

    public int sum(String exeQuery){
        int sum = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(exeQuery,null);

        if(cursor.moveToFirst()){
            sum = cursor.getInt(0);
        }
        cursor.close();
        return sum;
    }
}
