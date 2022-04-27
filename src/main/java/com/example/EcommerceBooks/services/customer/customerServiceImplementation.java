package com.example.EcommerceBooks.services.customer;

import com.example.EcommerceBooks.dto.customerDto;
import com.example.EcommerceBooks.entity.customer;
import com.example.EcommerceBooks.exception.ResourceNotFoundException;
import com.example.EcommerceBooks.repository.customerRepository;
import com.example.EcommerceBooks.repository.productRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class customerServiceImplementation implements customerService{
    private customerRepository customer_Repository;

    public customerServiceImplementation(customerRepository customer_Repository) {
        this.customer_Repository = customer_Repository;
    }
    @Override
    public customerDto insertCustomer(customerDto customer_Dto) {
        customer custom = mapToEntity(customer_Dto);
        customer newCustom = customer_Repository.save(custom);

        customerDto customerResponse = mapToDTO(newCustom);
        return customerResponse;
    }

    @Override
    public List<customerDto> getAllCustomers() {
        List<customer> customs = customer_Repository.findAll();
        return customs.stream().map(custom -> mapToDTO(custom)).collect(Collectors.toList());
    }

    @Override
    public customerDto getCustomerById(int id) {
        customer customs = customer_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(customs);
    }


    @Override
    public customerDto updateCustomer(customerDto customer_Dto, int id) {
        customer custom = customer_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        custom.setName(customer_Dto.getName());
        custom.setAddress(customer_Dto.getAddress());
        custom.setPhoneNumber(customer_Dto.getPhoneNumber());


        customer updatedCustomer = customer_Repository.save(custom);
        return mapToDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomerById(int id) {
        customer customs = customer_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        customer_Repository.delete(customs);
    }

    private customerDto mapToDTO(customer custom){
        customerDto customer_Dto = new customerDto();
        customer_Dto.setId(custom.getId());
        customer_Dto.setName(custom.getName());
        customer_Dto.setAddress(custom.getAddress());
        customer_Dto.setPhoneNumber(custom.getPhoneNumber());


        return customer_Dto;
    }

    private customer mapToEntity(customerDto customer_Dto){
        customer custom = new customer();
        custom.setName(customer_Dto.getName());
        custom.setAddress(customer_Dto.getAddress());
        custom.setPhoneNumber(customer_Dto.getPhoneNumber());


        return custom;

    }
}
