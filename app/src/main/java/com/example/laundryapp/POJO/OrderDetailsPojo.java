package com.example.laundryapp.POJO;

public class OrderDetailsPojo {
    String clthname,clthcatgname,clthquantity,clthprice,clthtotalprice;

    public OrderDetailsPojo(){}

    public OrderDetailsPojo(String clthname, String clthcatgname, String clthquantity, String clthprice, String clthtotalprice) {
        this.clthname = clthname;
        this.clthcatgname = clthcatgname;
        this.clthquantity = clthquantity;
        this.clthprice = clthprice;
        this.clthtotalprice = clthtotalprice;
    }

    public String getClthname() {
        return clthname;
    }

    public void setClthname(String clthname) {
        this.clthname = clthname;
    }

    public String getClthcatgname() {
        return clthcatgname;
    }

    public void setClthcatgname(String clthcatgname) {
        this.clthcatgname = clthcatgname;
    }

    public String getClthquantity() {
        return clthquantity;
    }

    public void setClthquantity(String clthquantity) {
        this.clthquantity = clthquantity;
    }

    public String getClthprice() {
        return clthprice;
    }

    public void setClthprice(String clthprice) {
        this.clthprice = clthprice;
    }

    public String getClthtotalprice() {
        return clthtotalprice;
    }

    public void setClthtotalprice(String clthtotalprice) {
        this.clthtotalprice = clthtotalprice;
    }
}

