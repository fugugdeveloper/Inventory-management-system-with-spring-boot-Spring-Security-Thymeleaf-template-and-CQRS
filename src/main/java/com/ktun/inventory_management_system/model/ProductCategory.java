package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product_categories")
public class ProductCategory {
    @Id
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryImage;
    private String categoryCode;
    private String categoryStatus;
    private String createdBy;
    private String createdDate;
}
