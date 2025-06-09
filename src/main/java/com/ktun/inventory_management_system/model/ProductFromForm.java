package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFromForm {
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
    private MultipartFile productImage;
    private String productStatus;
    private String createdBy;
    private String createdDate;
}
