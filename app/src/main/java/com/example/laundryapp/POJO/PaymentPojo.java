package com.example.laundryapp.POJO;

public class PaymentPojo {
    String paymenttext;
    int paymentimage;

    public PaymentPojo(String paymenttext, int paymentimage) {
        this.paymenttext = paymenttext;
        this.paymentimage = paymentimage;
    }

    public String getPaymenttext() {
        return paymenttext;
    }

    public void setPaymenttext(String paymenttext) {
        this.paymenttext = paymenttext;
    }

    public int getPaymentimage() {
        return paymentimage;
    }

    public void setPaymentimage(int paymentimage) {
        this.paymentimage = paymentimage;
    }
}
