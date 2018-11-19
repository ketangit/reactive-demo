package com.mycompany.demo.reactivedemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Tree {
    private long treeid;
    private Date plantdate;
    private String qaddress;
    private String qlegalstatus;
    private String qsiteinfo;
    private String qspecies;
    private String planttype;
    private long siteorder;
}
