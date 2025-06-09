package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sales")
public class Sale {

    @Id
    private String saleId;
    private String customerName;
    private LocalDateTime saleDate;
    private String supplierName;
    private List<SaleItem> items;
    private double orderTax;
    private Long discount;
    private double shipping;
    private String status;
    private double grandTotal;
    private LocalDateTime createdAt;


    // Getters and Setters with lombok
}
