package com.example.EcommerceBooks.repository;

import com.example.EcommerceBooks.entity.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<products,Integer> {

}
