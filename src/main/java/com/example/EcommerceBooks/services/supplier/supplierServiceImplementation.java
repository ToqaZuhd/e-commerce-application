package com.example.EcommerceBooks.services.supplier;

import com.example.EcommerceBooks.dto.supplierDto;
import com.example.EcommerceBooks.entity.suppliers;
import com.example.EcommerceBooks.exception.ResourceNotFoundException;

import com.example.EcommerceBooks.repository.productRepository;
import com.example.EcommerceBooks.repository.supplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class supplierServiceImplementation implements supplierService{
    private supplierRepository supplier_Repository;

    public supplierServiceImplementation(supplierRepository supplier_Repository) {
        this.supplier_Repository = supplier_Repository;
    }
    @Override
    public supplierDto insertSupplier(supplierDto supplier_Dto) {
        suppliers supp = mapToEntity(supplier_Dto);
        suppliers newSupp = supplier_Repository.save(supp);

        supplierDto supplierResponse = mapToDTO(newSupp);
        return supplierResponse;
    }

    @Override
    public List<supplierDto> getAllSuppliers() {
        List<suppliers> supps = supplier_Repository.findAll();
        return supps.stream().map(supp -> mapToDTO(supp)).collect(Collectors.toList());
    }

    @Override
    public supplierDto getSupplierById(int id) {
        suppliers supp = supplier_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(supp);
    }


    @Override
    public supplierDto updateSupplier(supplierDto supplier_Dto, int id) {
        suppliers supp = supplier_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        supp.setSupplierName(supplier_Dto.getSupplierName());
        supp.setSupplierAddress(supplier_Dto.getSupplierAddress());
        supp.setSupplierPhone(supplier_Dto.getSupplierPhone());

        suppliers updatedSupplier = supplier_Repository.save(supp);
        return mapToDTO(updatedSupplier);
    }

    @Override
    public void deleteSupplierById(int id) {

    }
    private supplierDto mapToDTO(suppliers supp){
        supplierDto supplier_Dto = new supplierDto();
        supplier_Dto.setId(supp.getId());
        supplier_Dto.setSupplierName(supp.getSupplierName());
        supplier_Dto.setSupplierAddress(supp.getSupplierAddress());
        supplier_Dto.setSupplierPhone(supp.getSupplierPhone());
        supplier_Dto.setSupplierEmail(supp.getSupplierEmail());


        return supplier_Dto;
    }

    private suppliers mapToEntity(supplierDto supplier_Dto){
        suppliers supp = new suppliers();
        supp.setSupplierName(supplier_Dto.getSupplierName());
        supp.setSupplierAddress(supplier_Dto.getSupplierAddress());
        supp.setSupplierPhone(supplier_Dto.getSupplierPhone());
        supp.setSupplierEmail(supplier_Dto.getSupplierEmail());


        return supp;

    }
}
