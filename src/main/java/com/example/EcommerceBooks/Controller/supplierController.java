package com.example.EcommerceBooks.Controller;

import com.example.EcommerceBooks.dto.supplierDto;
import com.example.EcommerceBooks.exception.BadRequestException;
import com.example.EcommerceBooks.services.supplier.supplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class supplierController {
    private final Logger log = LoggerFactory.getLogger(supplierController.class);

    @Autowired
    private supplierService supplier_service;
    public supplierController(supplierService supplier_service) {

        this.supplier_service = supplier_service;
    }

    @GetMapping
    public ResponseEntity<List<supplierDto>> getAllSuppliers() {
        return ResponseEntity.ok().body(supplier_service.getAllSuppliers()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<supplierDto> getSupplierById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(supplier_service.getSupplierById(id));
    }


    @PostMapping
    public ResponseEntity<supplierDto> insertCustomer(@Valid @RequestBody supplierDto supplier_Dto) {
        if (supplier_Dto.getId() != null) {
            log.error("Cannot have an ID {}", supplier_Dto);
            throw new BadRequestException(supplierController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(supplier_service.insertSupplier(supplier_Dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<supplierDto> updateCustomer(@Valid @RequestBody supplierDto supplier_Dto
            , @PathVariable(name = "id") int id) {

        return new ResponseEntity<>(supplier_service.updateSupplier(supplier_Dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {
        supplier_service.deleteSupplierById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
