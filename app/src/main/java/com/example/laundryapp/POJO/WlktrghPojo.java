package com.example.laundryapp.POJO;

public class WlktrghPojo {
    String title, desc;
    int screenimg;

    public WlktrghPojo(String title, String desc, int screenimg) {
        this.title = title;
        this.desc = desc;
        this.screenimg = screenimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getScreenimg() {
        return screenimg;
    }

    public void setScreenimg(int screenimg) {
        this.screenimg = screenimg;
    }
}
