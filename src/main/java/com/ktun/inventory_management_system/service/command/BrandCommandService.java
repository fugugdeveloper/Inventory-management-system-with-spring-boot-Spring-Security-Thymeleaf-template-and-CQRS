package com.ktun.inventory_management_system.service.command;

import com.ktun.inventory_management_system.model.Brand;
import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandCommandService {
@Autowired
BrandRepository brandRepository;
public SaveResponse saveBrand(Brand brand) {
    Brand savedBrand = brandRepository.save(brand);
    if (savedBrand.getBrandId() == null) {
        System.out.println("Brand save failed.");
        return new SaveResponse(false, "Brand save failed.");
    }
    return new SaveResponse(true, "Brand saved successfully.");
}
public SaveResponse updateBrand(Brand brand) {
    Brand updatedBrand = brandRepository.save(brand);
    if (updatedBrand.getBrandId() == null) {
        return new SaveResponse(false, "Brand update failed.");
    }
    return new SaveResponse(true, "Brand updated successfully.");
}
}
