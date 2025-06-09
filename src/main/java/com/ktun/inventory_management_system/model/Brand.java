package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "brands")
public class Brand {
    @Id
    private String brandId;
    private String brandName;
    private String brandDescription;
    private String brandLogo;
}
