package com.ktun.inventory_management_system.service.query;

import com.ktun.inventory_management_system.exceptions.ResourceNotFoundException;
import com.ktun.inventory_management_system.model.ProductCategory;
import com.ktun.inventory_management_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryQueryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
    public ProductCategory getCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id: "+id+" is found."));
    }
}
