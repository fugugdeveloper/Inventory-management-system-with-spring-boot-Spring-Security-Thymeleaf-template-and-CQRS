package com.ktun.inventory_management_system.service.query;

import com.ktun.inventory_management_system.exceptions.ResourceNotFoundException;
import com.ktun.inventory_management_system.model.Brand;
import com.ktun.inventory_management_system.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandQueryService {
    @Autowired
    BrandRepository brandRepository;
    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }
    public Brand getBrandById(String id) {
        return brandRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Brand with id: "+id+" is not found."));
    }
}
