package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.model.Brand;
import com.ktun.inventory_management_system.model.Customer;
import com.ktun.inventory_management_system.model.CustomerForm;
import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.service.command.CustomerCommandService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
public class CustomerCommandController {
    @Autowired
    private CustomerCommandService customerCommandService;
    private static final String CUSTOMER_AVATAR_UPLOAD_DIR = "C:/Users/Fandishe Fugug/IdeaProjects/inventory_management_system/src/main/resources/static/customer-avatars";

    @PostMapping("/addcustomer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createCustomerCommand(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute CustomerForm customerForm, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();

        String customerId = "CUID" + (1000 + new Random().nextInt(9000));
        customer.setCustomerId(customerId);
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(customerForm.getCustomerAvatar().getOriginalFilename()));
            Path uploadPath = Paths.get(CUSTOMER_AVATAR_UPLOAD_DIR);
            System.out.println("Brand Logo UploadPath: " + uploadPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            customerForm.getCustomerAvatar().transferTo(uploadPath.resolve(fileName).toFile());
            customer.setCustomerAvatar("/customer-avatars/" + fileName);
            customer.setCustomerName(customerForm.getCustomerName());
            customer.setCustomerEmail(customerForm.getCustomerEmail());
            customer.setCustomerPhone(customerForm.getCustomerPhone());
            customer.setCustomerAddress(customerForm.getCustomerAddress());
            customer.setCustomerCountry(customerForm.getCustomerCountry());
            customer.setCreatedBy(userDetails.getUsername().toUpperCase());
            customer.setCustomerDescription(customerForm.getCustomerDescription());
            customer.setCreatedDate(LocalDateTime.now().toString());
            customer.setStatus("Active");
            SaveResponse customersaveResponse = customerCommandService.addCustomer(customer);
            if (customersaveResponse.isSuccess()) {
                System.out.println("Saved Customer: " + customersaveResponse.getMessage());
                redirectAttributes.addFlashAttribute("message", customersaveResponse.getMessage());
                return "redirect:/customerlist";
            }else {
                log.warn("error: ", customersaveResponse.getMessage());
                System.out.println("Failed to save customer: " + customersaveResponse.getMessage());
                redirectAttributes.addFlashAttribute("error", customersaveResponse.getMessage());
                return "redirect:/addcustomer";
            }
        } catch (IOException e) {
            System.out.println("Exception due to Image in addCustomer: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to upload customer image.");
            return "redirect:/addcustomer";
        }
    }
}
