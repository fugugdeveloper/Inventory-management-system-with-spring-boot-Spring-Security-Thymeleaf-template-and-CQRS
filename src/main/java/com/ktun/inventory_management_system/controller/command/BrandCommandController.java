package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.model.Brand;
import com.ktun.inventory_management_system.model.BrandForm;
import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.model.User;
import com.ktun.inventory_management_system.repository.BrandRepository;
import com.ktun.inventory_management_system.service.command.BrandCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Controller
public class BrandCommandController {
    @Autowired
    private BrandCommandService brandCommandService;
    private static final String BRAND_LOGO_UPLOAD_DIR = "C:/Users/Fandishe Fugug/IdeaProjects/inventory_management_system/src/main/resources/static/brand-logos";

    @PostMapping("/addbrand")
    public String createBrandCommand(@ModelAttribute BrandForm brandForm, RedirectAttributes redirectAttributes) {
        Brand brand = new Brand();
        String brandId = "BID" + (1000 + new Random().nextInt(9000));
        brand.setBrandId(brandId);
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(brandForm.getBrandLogo().getOriginalFilename()));
            Path uploadPath = Paths.get(BRAND_LOGO_UPLOAD_DIR);
            System.out.println("Brand Logo UploadPath: " + uploadPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            brandForm.getBrandLogo().transferTo(uploadPath.resolve(fileName).toFile());
            brand.setBrandLogo("/brand-logos/" + fileName);
            brand.setBrandName(brandForm.getBrandName());
            brand.setBrandDescription(brandForm.getBrandDescription());

            SaveResponse saveResponse = brandCommandService.saveBrand(brand);
            if (saveResponse.isSuccess()) {
                System.out.println("Saved brand: " + saveResponse.getMessage());
                redirectAttributes.addFlashAttribute("message", saveResponse.getMessage());
                return "redirect:/brandlist";
            }else {
                log.warn("error: ", saveResponse.getMessage());
                System.out.println("Failed to save brand: " + saveResponse.getMessage());
                redirectAttributes.addFlashAttribute("error", saveResponse.getMessage());
                return "redirect:/addbrand";
            }
        } catch (IOException e) {
            System.out.println("Exception due to Image in addBrand: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to upload product image.");
            return "redirect:/addbrand";
        }
    }

}
