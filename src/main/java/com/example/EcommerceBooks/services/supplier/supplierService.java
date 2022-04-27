package com.example.EcommerceBooks.services.supplier;



import com.example.EcommerceBooks.dto.supplierDto;

import java.util.List;

public interface supplierService {
    supplierDto insertSupplier(supplierDto supplier_Dto);

    List<supplierDto> getAllSuppliers();

    supplierDto getSupplierById(int id);


    supplierDto updateSupplier(supplierDto supplier_Dto, int id);

    void deleteSupplierById(int id);
}
