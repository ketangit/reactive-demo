package com.mycompany.demo.reactivedemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Farmer {
    private Long farmer_id;
    private String website;
    private String suite;
    private String category;
    private String business;
    private String item;
    private Point location_1;
    private String location_1_city;
    private String location_1_location;
    private String location_1_state;
    private String zipcode;
}
