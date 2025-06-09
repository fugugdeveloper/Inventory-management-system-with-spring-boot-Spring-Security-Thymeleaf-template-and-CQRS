package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {
    private String categoryName;
    private String categoryDescription;
    private MultipartFile categoryImage;
    private String categoryCode;
    private String categoryStatus;
    private String createdBy;
    private String createdDate;
}
