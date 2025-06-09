package com.ktun.inventory_management_system.service.command;

import com.ktun.inventory_management_system.model.ProductCategory;
import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryCommandService {
    @Autowired
    CategoryRepository categoryRepository;
    public SaveResponse saveCategory(ProductCategory category) {
        try {
            ProductCategory savedCategory = categoryRepository.save(category);
            if (savedCategory.getCategoryId() != null) {
                return new SaveResponse(true, "Category saved successfully.");
            } else {
                return new SaveResponse(false, "Category save failed.");
            }
        } catch (Exception e) {
            return new SaveResponse(false, "Error occurred: " + e.getMessage());
        }
    }

}
