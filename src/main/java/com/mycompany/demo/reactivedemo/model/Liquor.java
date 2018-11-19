package com.mycompany.demo.reactivedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Liquor {
    @JsonProperty("brand_label_expiration_date")
    private Date brandLabelExpirationDate;

    @JsonProperty("brand_label_name")
    private String beandLabelName;

    @JsonProperty("brand_label_serial_number")
    private String brandLabelSerialNumber;

    private String domestic_d_or_imported_i;
    private String license_class_code;
    private String license_class_description;
    private String license_type_code;

    @JsonProperty("product_description")
    private String productDescription;
    private String wholesaler_license_serial_number;
    private String wholesaler_name;
}
