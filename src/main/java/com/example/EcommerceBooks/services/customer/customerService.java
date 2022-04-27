package com.example.EcommerceBooks.services.customer;

import com.example.EcommerceBooks.dto.customerDto;

import java.util.List;


public interface customerService {
    customerDto insertCustomer(customerDto product_Dto);

    List<customerDto> getAllCustomers();

    customerDto getCustomerById(int id);


    customerDto updateCustomer(customerDto customer_Dto, int id);

    void deleteCustomerById(int id);
}
