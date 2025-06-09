package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.model.*;
import com.ktun.inventory_management_system.repository.BrandRepository;
import com.ktun.inventory_management_system.service.command.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
@Controller
public class ProductCommandController {

    @Autowired
    private ProductCommandService productCommandService;
    @Autowired
    private BrandRepository brandRepository;

    private static final String PRODUCT_UPLOAD_DIR = "C:/Users/Fandishe Fugug/IdeaProjects/inventory_management_system/src/main/resources/static/product-images";


   @PostMapping("/newProduct")
   @PreAuthorize("hasRole('ADMIN')")
    public String createNewProductCommand(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ProductFromForm productFromForm,
                             RedirectAttributes redirectAttributes) {
        Product product = new Product();
        String productId = "PD" + (1000 + new Random().nextInt(9000));

        LocalDateTime now = LocalDateTime.now();
        try {
            // Upload image to server folder
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(productFromForm.getProductImage().getOriginalFilename()));
            Path uploadPath = Paths.get(PRODUCT_UPLOAD_DIR);
            System.out.println("Product Image UploadPath: " + uploadPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            productFromForm.getProductImage().transferTo(uploadPath.resolve(fileName).toFile());

            // Set product fields
            product.setProductId(productId);
            product.setProductCategory(productFromForm.getProductCategory());
            product.setProductSubCategory(productFromForm.getProductSubCategory()); // Make sure this field exists
            product.setProductName(productFromForm.getProductName());
            product.setProductDescription(productFromForm.getProductDescription());
            product.setProductPrice(productFromForm.getProductPrice());
            product.setBrand_Id(productFromForm.getBrand_Id());
            product.setProductUnit(productFromForm.getProductUnit());
            product.setProductType(productFromForm.getProductType());
            product.setProductTax(productFromForm.getProductTax());
            product.setProductDiscountType(productFromForm.getProductDiscountType());
            product.setProductStatus(productFromForm.getProductStatus());
            product.setCreatedBy(userDetails.getUsername().toUpperCase());
            product.setCreatedDate(now.toString());
            product.setProductMinimumQuantity(productFromForm.getProductMinimumQuantity());
            product.setProductQuantity(productFromForm.getProductQuantity());
            product.setProductImage("/product-images/" + fileName);

            // Generate SKU based on category, sub-category, brand
            String sku = generateSKU(
                    productFromForm.getProductCategory(),
                    productFromForm.getProductSubCategory(),
                    productFromForm.getBrand_Id()
            );
            product.setProductSKU(sku);

            // Save product to DB
            productCommandService.addProduct(product);

            redirectAttributes.addFlashAttribute("message", "Product created successfully!");
            return "redirect:/productlist";

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to upload product image.");
            return "redirect:/addproduct";
        }
    }
    private String clean(String str, int maxLength) {
        if (str == null) return "";
        return str.trim()
                .toUpperCase()
                .replaceAll("\\s+", "-")
                .substring(0, Math.min(str.length(), maxLength));
    }

    // SKU generation helper method
    private String generateSKU(String category, String subCategory, String brand) {
        // Null-safe helper to clean and trim

        String catCode = clean(category, 4);
        String subCatCode = clean(subCategory, 4);
        String brandCode = clean(brand, 3);

        return String.format("%s-%s-%s", catCode, subCatCode, brandCode);
    }






}
