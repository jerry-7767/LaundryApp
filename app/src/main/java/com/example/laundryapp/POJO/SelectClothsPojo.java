package com.example.laundryapp.POJO;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SelectClothsPojo {
    private String clthimage;
    private String clthname;
    private Long clthprice;

    public SelectClothsPojo(){

    }

    public SelectClothsPojo(String clthimage, String clthname, Long clthprice) {
        this.clthimage = clthimage;
        this.clthname = clthname;
        this.clthprice = clthprice;
    }

    public String getClthimage() {
        return clthimage;
    }

    public void setClthimage(String clthimage) {
        this.clthimage = clthimage;
    }

    public String getClthname() {
        return clthname;
    }

    public void setClthname(String clthname) {
        this.clthname = clthname;
    }

    public Long getClthprice() {
        return clthprice;
    }

    public void setClthprice(Long clthprice) {
        this.clthprice = clthprice;
    }
}