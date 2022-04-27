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
public class suppliers {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
    )
    private int id;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_phone")
    private String supplierPhone;

    @Column(name = "supplier_address")
    private String supplierAddress;

    @Column(name = "supplier_email")
    private String supplierEmail;
}
