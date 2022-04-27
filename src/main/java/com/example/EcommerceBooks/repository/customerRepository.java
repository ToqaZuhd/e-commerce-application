package com.example.EcommerceBooks.repository;

import com.example.EcommerceBooks.entity.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<customer, Integer> {
}
