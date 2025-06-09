package com.ktun.inventory_management_system.controller.query;

import com.ktun.inventory_management_system.model.Customer;
import com.ktun.inventory_management_system.model.CustomerForm;
import com.ktun.inventory_management_system.service.query.CustomerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerQueryController {
    @Autowired
    CustomerQueryService customerQueryService;
    @GetMapping("/addcustomer")
    @PreAuthorize( "hasAnyRole('ROLE_ADMIN')")
    public String addCustomerQuery(@AuthenticationPrincipal UserDetails userDetails, Model model ) {
        model.addAttribute("customerForm", new CustomerForm());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "addcustomer";
    }
    @GetMapping("/editcustomer/{id}")
    public String getEditCustomerQuery(@AuthenticationPrincipal UserDetails userDetails,@PathVariable("id") String id,  Model model ) {
        Customer customer = customerQueryService.getCustomerById(id);
        model.addAttribute("customerForm", new CustomerForm());
        model.addAttribute("customerId", customer.getCustomerId());
        model.addAttribute("cName", customer.getCustomerName());
        model.addAttribute("cDescription", customer.getCustomerDescription());
        model.addAttribute("cAddress", customer.getCustomerAddress());
        model.addAttribute("cPhone", customer.getCustomerPhone());
        model.addAttribute("cEmail", customer.getCustomerEmail());
        model.addAttribute("cAvatar", customer.getCustomerAvatar());
        model.addAttribute("cCountry", customer.getCustomerCountry());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "editcustomer";
    }
    @GetMapping("/customerlist")
    public String getCustomerListQuery(@AuthenticationPrincipal UserDetails userDetails, Model model ) {
        model.addAttribute("customers", customerQueryService.getAllCustomers());
        model.addAttribute("username", userDetails.getUsername().toUpperCase());
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority().toUpperCase().substring(5));
        return "customerlist";
    }

}
