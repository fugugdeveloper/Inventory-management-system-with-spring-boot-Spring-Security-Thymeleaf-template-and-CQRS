package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "supplier-details")
public class Supplier {
    @Id
    private String supplierId;               // Supplier ID
    private String supplierName;             // Supplier name
    private String supplierNIC;              // Supplier NIC (National Identification Card)
    private String supplierContactNumber;    // Supplier contact number
    private String email;                    // Supplier email address
    private String address;                  // Supplier address
    private String city;                     // Supplier city
    private String country;                  // Supplier country
    private String postalCode;               // Supplier postal code
    private String supplierType;             // Type of supplier (e.g., local, international)
    private String supplierStatus;           // Status of the supplier (e.g., active, inactive)
    private String createdBy;                // User who created the supplier record
    private String createdDate;              // Date when the supplier record was created
    private String modifiedBy;               // User who last modified the supplier record
    private String modifiedDate;             // Date when the supplier record was last modified
    private String supplierDescription;      // Description of the supplier
    private String supplierImage;            // Image of the supplier (optional)
    private String supplierCategory;         // Category of the supplier (e.g., electronics, groceries)
    private String supplierRating;           // Rating of the supplier (e.g., 1 to 5 stars)
    private String supplierWebsite;          // Supplier's website URL
    private String supplierTaxId;           // Supplier's tax identification number
    private String supplierPaymentTerms;     // Payment terms agreed with the supplier
    private String supplierContractStartDate; // Start date of the supplier contract
    private String supplierContractEndDate;   // End date of the supplier contract
    private String supplierBankDetails;      // Bank details for payments to the supplier
    private String supplierNotes;            // Additional notes about the supplier
    private String supplierSocialMediaLinks; // Links to the supplier's social media profiles
    private String supplierLanguage;         // Preferred language of the supplier
    private String supplierCurrency;         // Preferred currency for transactions with the supplier
    private String supplierComplianceStatus; // Compliance status of the supplier (e.g., certified, pending)
    private String supplierInsuranceDetails; // Insurance details of the supplier
    private String supplierEmergencyContact; // Emergency contact details for the supplier
    private String supplierFeedback;         // Feedback or reviews from customers about the supplier
    private String supplierLastOrderDate;    // Date of the last order placed with the supplier
}
