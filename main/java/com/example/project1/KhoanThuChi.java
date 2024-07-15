package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class KhoanThuChi {
    //static
    @NonNull
    public static ArrayList<KhoanThuChi> khoanThuChiArrayList = new ArrayList<>();
    public static int nguongChi_ngay = 2147483647;
    public static int nguongChi_thang = 2147483647 ;
    public static int nguongChi_tuan = 2147483647 ;

    @NonNull
    public static SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MM-yyyy");

    @NonNull
    public static String DateToString(@NonNull Date date){
        return format.format(date);
    }

    @Nullable
    public static Date StringToDate(@NonNull String date){
        try {
            return (Date) format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //attributes
    private int ID;
    private Date ngay;
    private String thu_or_chi;
    private String loaiKhoan;
    private int giaTri;

    //Getter Setter
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getThu_or_chi() {
        return thu_or_chi;
    }

    public void setThu_or_chi(String thu_or_chi) {
        this.thu_or_chi = thu_or_chi;
    }

    public Date getNgay() {
        return ngay;
    }
    @NonNull
    public String getNgay_String(){
        return ngay.toString();
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getLoaiKhoan() {
        return loaiKhoan;
    }

    public void setLoaiKhoan(String loaiKhoan) {
        this.loaiKhoan = loaiKhoan;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    //Constructor
    public KhoanThuChi() {
    }

    public KhoanThuChi(int ID, String thu_or_chi, Date ngay, String loaiKhoan, int giaTri) {
        this.ID = ID;
        this.thu_or_chi = thu_or_chi;
        this.ngay = ngay;
        this.loaiKhoan = loaiKhoan;
        this.giaTri = giaTri;
    }

    public KhoanThuChi(Date ngay, String thu_or_chi, String loaiKhoan, int giaTri) {
        this.ngay = ngay;
        this.thu_or_chi = thu_or_chi;
        this.loaiKhoan = loaiKhoan;
        this.giaTri = giaTri;
    }
}
