package com.example.EcommerceBooks.Controller;

import com.example.EcommerceBooks.dto.customerDto;
import com.example.EcommerceBooks.exception.BadRequestException;
import com.example.EcommerceBooks.services.customer.customerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class customerController {
    private final Logger log = LoggerFactory.getLogger(customerController.class);


    @Autowired
    private customerService customer_service;
    public customerController(customerService customer_service) {

        this.customer_service = customer_service;
    }

    @GetMapping
    public ResponseEntity<List<customerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customer_service.getAllCustomers()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<customerDto> getCustomerById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(customer_service.getCustomerById(id));
    }



    @PostMapping
    public ResponseEntity<customerDto> insertCustomer(@Valid @RequestBody customerDto customer_Dto) {
        if (customer_Dto.getId() != null) {
            log.error("Cannot have an ID {}", customer_Dto);
            throw new BadRequestException(supplierController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(customer_service.insertCustomer(customer_Dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<customerDto> updateCustomer(@Valid @RequestBody customerDto customer_Dto
            , @PathVariable(name = "id") int id) {

        return new ResponseEntity<>(customer_service.updateCustomer(customer_Dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {
        customer_service.deleteCustomerById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
