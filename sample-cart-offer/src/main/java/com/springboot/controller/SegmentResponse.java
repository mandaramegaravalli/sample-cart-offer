package com.springboot.controller;

import lombok.Data;

@Data
public class SegmentResponse {
    private String segment;

    public String getSegment() {
        return segment;
    }
}
