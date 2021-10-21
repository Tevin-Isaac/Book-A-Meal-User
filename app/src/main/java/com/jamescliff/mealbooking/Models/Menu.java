package com.jamescliff.mealbooking.Models;

import java.io.Serializable;

public class Menu implements Serializable {
    private int id;
    private String name, course, status, image, content_description, location;
    private int price, num_order;

    public Menu(int id, String name, String course, String status, int price, String image, int num_order, String content_description, String location) {
        this.name = name;
        this.id = id;
        this.course = course;
        this.status = status;
        this.price = price;
        this.image = image;
        this.num_order = num_order;
        this.content_description = content_description;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNum_order() {
        return num_order;
    }

    public void setNum_order(int num_order) {
        this.num_order = num_order;
    }

    public String getContent_description() {
        return content_description;
    }

    public void setContent_description(String content_description) {
        this.content_description = content_description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
