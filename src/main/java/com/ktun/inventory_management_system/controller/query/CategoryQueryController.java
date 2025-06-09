package com.ktun.inventory_management_system.controller.query;

import com.ktun.inventory_management_system.model.CategoryForm;
import com.ktun.inventory_management_system.service.query.CategoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryQueryController {
@Autowired
CategoryQueryService categoryQueryService;

    @GetMapping("/addsubcategory")
    public String addSubCategoryPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "addsubcategory";
    }
    @GetMapping("/categorylist")
    public String categoryListPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("categories", categoryQueryService.getAllCategories());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "categorylist";
    }
    @GetMapping("/addcategory")
    public String addCategoryPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "addcategory";
    }
    @GetMapping("/editcategory/{id}")
    public String editCategoryPage(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") String categoryId, Model model) {
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        model.addAttribute("category", categoryQueryService.getCategoryById(categoryId));
        return "editcategory";
    }

}
