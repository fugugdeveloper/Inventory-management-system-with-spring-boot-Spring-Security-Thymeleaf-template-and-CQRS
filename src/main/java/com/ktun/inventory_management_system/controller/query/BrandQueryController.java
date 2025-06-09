package com.ktun.inventory_management_system.controller.query;

import com.ktun.inventory_management_system.model.Brand;
import com.ktun.inventory_management_system.model.BrandForm;
import com.ktun.inventory_management_system.repository.BrandRepository;
import com.ktun.inventory_management_system.service.query.BrandQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BrandQueryController {
    @Autowired
    private BrandQueryService brandQueryService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/addbrand")
    public String addBrandQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("brandForm", new BrandForm());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "addbrand";
    }
@GetMapping("/brandlist")
    public String brandListQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("brands", brandQueryService.getAllBrands());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "brandlist";
    }
    @GetMapping("/editbrand/{id}")
    public String editBrandQuery(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") String brandId, Model model) {

        Brand brand = brandQueryService.getBrandById(brandId);
        model.addAttribute("brandForm", new BrandForm());
        model.addAttribute("brandId", brand.getBrandId());
        model.addAttribute("bName", brand.getBrandName());
        model.addAttribute("bDescription", brand.getBrandDescription());
        model.addAttribute("bLogo", brand.getBrandLogo());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        model.addAttribute("brand", brandQueryService.getBrandById(brandId));
        return "editbrand";
    }
}
