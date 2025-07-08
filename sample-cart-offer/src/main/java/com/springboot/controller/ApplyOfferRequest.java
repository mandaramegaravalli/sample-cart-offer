package com.springboot.controller;

import lombok.Data;

@Data
public class ApplyOfferRequest {
    private int cart_value;
    private int restaurant_id;
    private int user_id;

    public int getCart_value() {
        return cart_value;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }
}
