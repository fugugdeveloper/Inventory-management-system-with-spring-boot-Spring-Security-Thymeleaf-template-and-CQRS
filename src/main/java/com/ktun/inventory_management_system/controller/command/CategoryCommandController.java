package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.model.CategoryForm;
import com.ktun.inventory_management_system.model.ProductCategory;
import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.service.command.CategoryCommandService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Controller
public class CategoryCommandController {

    @Autowired
    private CategoryCommandService categoryCommandService;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String CATEGORY_UPLOAD_DIR = "C:/Users/Fandishe Fugug/IdeaProjects/inventory_management_system/src/main/resources/static/category-images";
    @PostMapping("/addcategory")
    public String addCategory(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute CategoryForm categoryForm, RedirectAttributes redirectAttributes) {
        ProductCategory productCategory = new ProductCategory();
        String categoryID = "CID" + (1000 + new Random().nextInt(9000));

        LocalDateTime now = LocalDateTime.now();
        try {
            // Upload image to server folder
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(categoryForm.getCategoryImage().getOriginalFilename()));
            Path uploadPath = Paths.get(CATEGORY_UPLOAD_DIR);
            System.out.println("Category Image UploadPath: " + uploadPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            productCategory.setCategoryId(categoryID);
            productCategory.setCategoryName(categoryForm.getCategoryName());
            productCategory.setCategoryImage("/category-images/" + fileName);
            productCategory.setCategoryDescription(categoryForm.getCategoryDescription());
            String categoryCode = generateCategoryCode(categoryForm.getCategoryName());
            productCategory.setCategoryCode(categoryCode);
            productCategory.setCreatedBy(userDetails.getUsername().toUpperCase());
            productCategory.setCreatedDate(now.toString());
            productCategory.setCategoryStatus(categoryForm.getCategoryStatus());
            SaveResponse saveResponse = categoryCommandService.saveCategory(productCategory);
            if (saveResponse.isSuccess()) {
                redirectAttributes.addFlashAttribute("message", saveResponse.getMessage());
                return "redirect:/categorylist";
            }else {
                redirectAttributes.addFlashAttribute("error", saveResponse.getMessage());
                return "redirect:/addcategory";
            }
        } catch (IOException e) {
            System.out.println("Exception due to Image in addCategory: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to upload product image.");
            return "redirect:/addcategory";
        }

    }
    private String clean(String str) {
        if (str == null) return "";
        return str.trim().toUpperCase().replaceAll("\\s+", "_");
    }

    private String randomAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
    public String generateCategoryCode(String categoryName) {
        return clean(categoryName) + "_" + randomAlphaNumeric(4);
    }
}
