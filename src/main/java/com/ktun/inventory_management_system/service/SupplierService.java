package com.ktun.inventory_management_system.service;

import com.ktun.inventory_management_system.model.Supplier;
import com.ktun.inventory_management_system.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private SupplierRepository supplierRepository;
    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }
}
