package com.example.EcommerceBooks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class products {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
    )
    private int id;

    @Column(name = "book_name")
    private String BookName;

    @Column(name = "description")
    private String description;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private double price;

    @Column(name = "sold_product")
    private int sold_product;

    @Column(name = "remaining_products")
    private int remaining_products;


}
