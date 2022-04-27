package com.example.EcommerceBooks.dto;

import lombok.Data;

@Data
public class productDto {

    private Integer id;
    private String BookName;
    private String description;
    private String authorName;
    private String picture;
    private double price;
    private int sold_product;
    private int remaining_products;
}
