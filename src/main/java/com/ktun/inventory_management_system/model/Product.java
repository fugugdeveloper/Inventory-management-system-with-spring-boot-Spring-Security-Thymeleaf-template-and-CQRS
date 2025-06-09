package com.ktun.inventory_management_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product-details")
public class Product {
    @Id
    private String productId;
    private String productCategory;
    private String productSubCategory;
    private String productName;
    private String brand_Id;
    private String productDescription;
    private String productUnit;
    private String productSKU;
    private Long productQuantity;
    private Long productMinimumQuantity;
    private Long productPrice;
    private String productType;
    private String productTax;
    private String productDiscountType;
    private String productImage;
    private String productStatus;
    private String createdBy;
    private String createdDate;
    }