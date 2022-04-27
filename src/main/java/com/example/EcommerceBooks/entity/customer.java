package com.example.EcommerceBooks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
    )
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;


}
