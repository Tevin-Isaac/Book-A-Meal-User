package com.jamescliff.mealbooking.Models;

public class Cart {
    private int food_id, user, amount;
    private String food_name, image;


    public Cart(int food_id, int user, String food_name, int amount, String image) {
        this.food_id = food_id;
        this.user = user;
        this.food_name = food_name;
        this.amount = amount;
        this.image = image;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
