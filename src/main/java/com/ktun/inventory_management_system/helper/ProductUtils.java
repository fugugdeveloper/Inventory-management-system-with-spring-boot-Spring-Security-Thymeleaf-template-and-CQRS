package com.ktun.inventory_management_system.helper;

import com.ktun.inventory_management_system.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductUtils {
    public static Map<String, Object> productToMap(Product product) {
        Map<String, Object> input = new HashMap<>();
        input.put("productId", product.getProductId());
        input.put("productCategory", product.getProductCategory());
        input.put("productName", product.getProductName());
        input.put("productDescription", product.getProductDescription());
        input.put("productImage", product.getProductImage());
        input.put("productStatus", product.getProductStatus());
        input.put("productSubCategory", product.getProductSubCategory());
        input.put("brand_id", product.getBrand_Id());
        input.put("productUnit", product.getProductUnit());
        input.put("productSKU", product.getProductSKU());
        input.put("productQuantity", product.getProductQuantity());
        input.put("productMinimumQuantity", product.getProductMinimumQuantity());
        input.put("productPrice", product.getProductPrice());
        input.put("productType", product.getProductType());
        input.put("productTax", product.getProductTax());
        input.put("productDiscountType", product.getProductDiscountType());
        input.put("createdBy", product.getCreatedBy());
        input.put("createdDate", product.getCreatedDate());
                return input;
    }
}
