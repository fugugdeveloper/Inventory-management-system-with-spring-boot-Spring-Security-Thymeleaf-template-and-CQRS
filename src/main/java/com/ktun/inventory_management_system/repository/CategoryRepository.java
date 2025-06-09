package com.ktun.inventory_management_system.repository;

import com.ktun.inventory_management_system.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<ProductCategory, String> {
}
