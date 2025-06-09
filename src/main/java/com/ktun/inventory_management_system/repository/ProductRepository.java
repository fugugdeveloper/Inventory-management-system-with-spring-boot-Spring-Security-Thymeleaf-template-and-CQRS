package com.ktun.inventory_management_system.repository;

import com.ktun.inventory_management_system.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductNameContainingIgnoreCaseOrProductCategoryContainingIgnoreCase(String name, String category);
    List<Product> findByProductCategoryContainingIgnoreCase(String category);
    List<Product> findByProductNameContainingIgnoreCase(String name);
}