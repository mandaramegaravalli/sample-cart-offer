package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class ApplyOfferResponse {
    private int cart_value;

    public ApplyOfferResponse(int cart_value){
        this.cart_value=cart_value;
    }



}
