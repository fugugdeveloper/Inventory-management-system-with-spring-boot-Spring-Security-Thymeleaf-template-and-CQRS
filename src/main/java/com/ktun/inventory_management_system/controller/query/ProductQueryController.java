package com.ktun.inventory_management_system.controller.query;

import com.ktun.inventory_management_system.model.*;
import com.ktun.inventory_management_system.service.query.CategoryQueryService;
import com.ktun.inventory_management_system.service.query.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;


@Controller

public class ProductQueryController {

    @Autowired
    private ProductQueryService productQueryService;
    @Autowired
    private CategoryQueryService categoryQueryService;



    private static final SecureRandom RANDOM = new SecureRandom();


    @GetMapping("/addproduct")
    public String addProductQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login"; // fallback, shouldn't occur if security config is correct
        }
        model.addAttribute("product", new ProductFromForm());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "addproduct";
    }









    @GetMapping("/store")
    public String getStoreQuery() {
        return "store";
    }

    @GetMapping("/bill")
    public String getBillQuery() {
        return "bill";
    }

    @GetMapping("/productlist")
    public String productlistQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Product> productList = productQueryService.getAllProducts();
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        model.addAttribute("products", productList);
        return "productlist";
    }

    @GetMapping("/product-details/{id}")
    public String productDetailsQuery(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") String productId, Model model) {
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        model.addAttribute("product", productQueryService.getProductById(productId));

        return "product-details";
    }




    @GetMapping("/addproducttype")
    public String addProductTypeQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "addproducttype";
    }

    @GetMapping("/addtax")
    public String addTaxQuery(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "addtax";
    }

    @GetMapping("/editproduct/{id}")
    public String editProductQuery(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") String productId, Model model) {
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        model.addAttribute("product", productQueryService.getProductById(productId));
        model.addAttribute("categories", categoryQueryService.getAllCategories());
        return "editproduct";
    }





}
