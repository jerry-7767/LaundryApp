package com.example.laundryapp.POJO;

public class CartPojo {
    String clothname,clothprice,clothquantity,clothcatgname,clothtotalprice;

    public CartPojo(){}

    public CartPojo(String clothname, String clothprice, String clothquantity, String clothcatgname, String clothtotalprice) {
        this.clothname = clothname;
        this.clothprice = clothprice;
        this.clothquantity = clothquantity;
        this.clothtotalprice = clothtotalprice;
        this.clothcatgname = clothcatgname;
    }

    public String getClothname() {
        return clothname;
    }

    public void setClothname(String clothname) {
        this.clothname = clothname;
    }

    public String getClothprice() {
        return clothprice;
    }

    public void setClothprice(String clothprice) {
        this.clothprice = clothprice;
    }

    public String getClothquantity() {
        return clothquantity;
    }

    public void setClothquantity(String clothquantity) {
        this.clothquantity = clothquantity;
    }

    public String getClothtotalprice() {
        return clothtotalprice;
    }

    public void setClothtotalprice(String clothtotalprice) {
        this.clothtotalprice = clothtotalprice;
    }

    public String getClothcatgname() {
        return clothcatgname;
    }

    public void setClothcatgname(String clothcatgname) {
        this.clothcatgname = clothcatgname;
    }
}

