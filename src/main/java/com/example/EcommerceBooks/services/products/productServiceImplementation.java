package com.example.EcommerceBooks.services.products;

import com.example.EcommerceBooks.dto.productDto;
import com.example.EcommerceBooks.entity.products;
import com.example.EcommerceBooks.exception.ResourceNotFoundException;
import com.example.EcommerceBooks.repository.productRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class productServiceImplementation implements productService {
    private productRepository product_Repository;

    public productServiceImplementation(productRepository product_Repository) {
        this.product_Repository = product_Repository;
    }

    @Override
    public productDto insertProduct(productDto product_Dto) {

        products prod = mapToEntity(product_Dto);
        products newProduct = product_Repository.save(prod);

        productDto productResponse = mapToDTO(newProduct);
        return productResponse;
    }

    @Override
    public List<productDto> getAllProducts() {
        List<products> prods = product_Repository.findAll();
        return prods.stream().map(prod -> mapToDTO(prod)).collect(Collectors.toList());

    }

    @Override
    public productDto getProductById(int id) {
        products prods = product_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(prods);
    }

    @Override
    public productDto updateProduct(productDto product_Dto, int id) {
        products prod = product_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        prod.setBookName(product_Dto.getBookName());
        prod.setDescription(product_Dto.getDescription());
        prod.setSold_product(product_Dto.getSold_product());
        prod.setRemaining_products(product_Dto.getRemaining_products());
        prod.setAuthorName(product_Dto.getAuthorName());
        prod.setPrice(product_Dto.getPrice());
        prod.setPicture(product_Dto.getPicture());

        products updatedProduct = product_Repository.save(prod);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(int id) {
        products prods = product_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        product_Repository.delete(prods);
    }

    private productDto mapToDTO(products prod){
        productDto product_Dto = new productDto();
        product_Dto.setId(prod.getId());
        product_Dto.setBookName(prod.getBookName());
        product_Dto.setDescription(prod.getDescription());
        product_Dto.setSold_product(prod.getSold_product());
        product_Dto.setRemaining_products(prod.getRemaining_products());
        product_Dto.setAuthorName(prod.getAuthorName());
        product_Dto.setPrice(prod.getPrice());
        product_Dto.setPicture(prod.getPicture());

        return product_Dto;
    }

    private products mapToEntity(productDto product_Dto){
        products prod = new products();
        prod.setBookName(product_Dto.getBookName());
        prod.setDescription(product_Dto.getDescription());
        prod.setSold_product(product_Dto.getSold_product());
        prod.setRemaining_products(product_Dto.getRemaining_products());
        prod.setAuthorName(product_Dto.getAuthorName());
        prod.setPrice(product_Dto.getPrice());
        prod.setPicture(product_Dto.getPicture());

        return prod;

    }
}
