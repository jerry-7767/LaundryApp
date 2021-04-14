package com.example.laundryapp.POJO;

public class OngoingOrderPojo {
    String orderId,deliveryDate,deliveryTime,orderStatus,orderCost;

    public OngoingOrderPojo(){}

    public OngoingOrderPojo(String orderId, String deliveryDate, String deliveryTime, String orderStatus, String orderCost) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.orderStatus = orderStatus;
        this.orderCost = orderCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(String orderCost) {
        this.orderCost = orderCost;
    }
}

