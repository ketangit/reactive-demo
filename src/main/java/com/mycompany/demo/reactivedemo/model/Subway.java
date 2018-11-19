package com.mycompany.demo.reactivedemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Subway {
    private String entrance_type;
    private String entry;
    private String free_crossover;
    private String line;
    private String north_south_street;
    private String route1;
    private String staffing;
    private String station_name;
    private Point station_location;
    private Point entrance_location;
    private String division;
    private String vending;
}
