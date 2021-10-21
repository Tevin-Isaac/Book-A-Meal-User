package com.jamescliff.mealbooking.Models;

public class Orders {
    private int id, customer;
    private String order_timestamp, delivery_timestamp, payment_status, delivery_status;
    boolean if_cancelled;
    int total_amount;
    String payment_method, location, delivery_boy;

    public Orders(int id, int customer, String order_timestamp, String delivery_timestamp, String payment_status, String delivery_status, boolean if_cancelled, int total_amount, String payment_method, String location, String delivery_boy) {
        this.id = id;
        this.customer = customer;
        this.order_timestamp = order_timestamp;
        this.delivery_timestamp = delivery_timestamp;
        this.payment_status = payment_status;
        this.delivery_status = delivery_status;
        this.if_cancelled = if_cancelled;
        this.total_amount = total_amount;
        this.payment_method = payment_method;
        this.location = location;
        this.delivery_boy = delivery_boy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getOrder_timestamp() {
        return order_timestamp;
    }

    public void setOrder_timestamp(String order_timestamp) {
        this.order_timestamp = order_timestamp;
    }

    public String getDelivery_timestamp() {
        return delivery_timestamp;
    }

    public void setDelivery_timestamp(String delivery_timestamp) {
        this.delivery_timestamp = delivery_timestamp;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public boolean isIf_cancelled() {
        return if_cancelled;
    }

    public void setIf_cancelled(boolean if_cancelled) {
        this.if_cancelled = if_cancelled;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDelivery_boy() {
        return delivery_boy;
    }

    public void setDelivery_boy(String delivery_boy) {
        this.delivery_boy = delivery_boy;
    }

}
