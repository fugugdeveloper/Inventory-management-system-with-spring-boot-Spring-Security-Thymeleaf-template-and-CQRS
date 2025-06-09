package com.ktun.inventory_management_system.helper;

import com.ktun.inventory_management_system.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierUtils {
    private static final WebClient webClient = WebClient.create("http://localhost:8080/graphql"); // Adjust as needed

    public static Map<String, Object> supplierToMap(Supplier supplier) {
        Map<String, Object> input = new HashMap<>();
        input.put("supplierId", supplier.getSupplierId());
        input.put("supplierName", supplier.getSupplierName());
        input.put("supplierNIC", supplier.getSupplierNIC());
        input.put("supplierContactNumber", supplier.getSupplierContactNumber());
        input.put("email", supplier.getEmail());
        input.put("address", supplier.getAddress());
        input.put("city", supplier.getCity());
        input.put("country", supplier.getCountry());
        input.put("postalCode", supplier.getPostalCode());
        input.put("supplierType", supplier.getSupplierType());
        input.put("supplierStatus", supplier.getSupplierStatus());
        input.put("createdBy", supplier.getCreatedBy());
        input.put("createdDate", supplier.getCreatedDate());
        input.put("modifiedBy", supplier.getModifiedBy());
        input.put("modifiedDate", supplier.getModifiedDate());
        input.put("supplierDescription", supplier.getSupplierDescription());
        input.put("supplierImage", supplier.getSupplierImage());
        input.put("supplierCategory", supplier.getSupplierCategory());
        input.put("supplierRating", supplier.getSupplierRating());
        input.put("supplierWebsite", supplier.getSupplierWebsite());
        input.put("supplierTaxId", supplier.getSupplierTaxId());
        input.put("supplierPaymentTerms", supplier.getSupplierPaymentTerms());
        input.put("supplierContractStartDate", supplier.getSupplierContractStartDate());
        input.put("supplierContractEndDate", supplier.getSupplierContractEndDate());
        input.put("supplierBankDetails", supplier.getSupplierBankDetails());
        input.put("supplierNotes", supplier.getSupplierNotes());
        input.put("supplierSocialMediaLinks", supplier.getSupplierSocialMediaLinks());
        input.put("supplierLanguage", supplier.getSupplierLanguage());
        input.put("supplierCurrency", supplier.getSupplierCurrency());
        input.put("supplierComplianceStatus", supplier.getSupplierComplianceStatus());
        input.put("supplierInsuranceDetails", supplier.getSupplierInsuranceDetails());
        input.put("supplierEmergencyContact", supplier.getSupplierEmergencyContact());
        input.put("supplierFeedback", supplier.getSupplierFeedback());
        input.put("supplierLastOrderDate", supplier.getSupplierLastOrderDate());
        return input;
    }

    public static Mono<List<Supplier>> fetchAllSuppliers() {
        String query = """
    {
           allSuppliers {
        supplierId
        supplierName
        supplierNIC
        supplierContactNumber
        email
        address
        city
        country
        postalCode
        supplierType
        supplierStatus
        createdBy
        createdDate
        modifiedBy
        modifiedDate
        supplierDescription
        supplierImage
        supplierCategory
        supplierRating
        supplierWebsite
        supplierTaxId
        supplierPaymentTerms
        supplierContractStartDate
        supplierContractEndDate
        supplierBankDetails
        supplierNotes
        supplierSocialMediaLinks
        supplierLanguage
        supplierCurrency
        supplierComplianceStatus
        supplierInsuranceDetails
        supplierEmergencyContact
        supplierFeedback
        supplierLastOrderDate
        }
    }
    """;

        return webClient.post()
                .bodyValue(Map.of("query", query))
                .retrieve()
                .bodyToMono(GraphQLResponse.class)
                .flatMap(response -> {
                    if (response.getData() == null || response.getData().getAllSuppliers() == null) {
                        return Mono.error(new IllegalStateException("Invalid response format"));
                    }
                    return Mono.just(response.getData().getAllSuppliers());
                })
                .onErrorResume(error -> {
                    // Enhanced error handling
                    System.err.println("Error fetching suppliers: " + error.getMessage());
                    return Mono.just(Collections.emptyList());
                });
    }

// Strongly-typed models for deserializing responses.
@Data @AllArgsConstructor @NoArgsConstructor
public static class GraphQLResponse {
    private DataWrapper data;

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class DataWrapper {
        private List<Supplier> allSuppliers;
    }
}
}