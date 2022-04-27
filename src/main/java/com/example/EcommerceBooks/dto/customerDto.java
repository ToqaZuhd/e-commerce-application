package com.example.EcommerceBooks.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class customerDto {

    private Integer id;
    private String Name;
    private String phoneNumber;
    private String address;

}
