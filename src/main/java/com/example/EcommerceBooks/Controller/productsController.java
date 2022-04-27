package com.example.EcommerceBooks.Controller;

import com.example.EcommerceBooks.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.EcommerceBooks.services.products.productService;
import com.example.EcommerceBooks.dto.productDto;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class productsController {

    private final Logger log = LoggerFactory.getLogger(productsController.class);


    @Autowired
    private productService product_service;
    public productsController(productService product_service) {

        this.product_service = product_service;
    }

    @GetMapping
    public ResponseEntity<List<productDto>> getAllProducts() {
        return ResponseEntity.ok().body(product_service.getAllProducts()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<productDto> getProductById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(product_service.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<productDto> insertProduct(@Valid @RequestBody productDto product_Dto) {
        if (product_Dto.getId() != null) {
            log.error("Cannot have an ID {}", product_Dto);
            throw new BadRequestException(productsController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(product_service.insertProduct(product_Dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<productDto> updateProduct(@Valid @RequestBody productDto product_Dto
            , @PathVariable(name = "id") int id) {

        return new ResponseEntity<>(product_service.updateProduct(product_Dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
        product_service.deleteProductById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
