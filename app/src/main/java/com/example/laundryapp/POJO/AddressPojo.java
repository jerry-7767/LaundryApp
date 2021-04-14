package com.example.laundryapp.POJO;

public class AddressPojo {
    String place;
    String address;

    public AddressPojo(String place, String address) {
        this.place = place;
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
