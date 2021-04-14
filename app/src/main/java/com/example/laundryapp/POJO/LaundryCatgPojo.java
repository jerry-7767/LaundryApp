package com.example.laundryapp.POJO;

public class LaundryCatgPojo {

    int images;
    String name;

    public LaundryCatgPojo( int images,String name) {
        this.images = images;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
