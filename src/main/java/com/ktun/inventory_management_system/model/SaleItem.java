package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleItem {
    private String productName;
    private Long quantity;
    private Long price;
    private Long discount;
    private double tax;
    private double subtotal;

    // Getters and Setters with lombok
}
