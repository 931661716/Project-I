package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LoaiKhoan {
    @NonNull
    public static ArrayList<LoaiKhoan> loaiKhoanArrayList = new ArrayList<>();
    @NonNull
    public static String EXTRA = "chonLoaiKhoan";
    private int ID;
    private String type;
    private String name;

    @Nullable
    public static LoaiKhoan getFromID(int id){
        for(LoaiKhoan loaiKhoan : loaiKhoanArrayList){
            if(loaiKhoan.getID() == id){
                return loaiKhoan;
            }
        }
        return null;
    }

    public LoaiKhoan() {
    }

    public LoaiKhoan(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
