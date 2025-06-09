package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.model.User;
import com.ktun.inventory_management_system.model.UserForm;
import com.ktun.inventory_management_system.service.command.UserCommandService;
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

@Controller
public class UserCommandController {


    private static final String USER_UPLOAD_DIR = "C:/Users/Fandishe Fugug/IdeaProjects/inventory_management_system/src/main/resources/static/user-images";
    @Autowired
    private UserCommandService userCommandService;

    @PostMapping("/adduser")
    public String addUserCommand(@ModelAttribute UserForm userForm, RedirectAttributes redirectAttributes) {
        User user = new User();
        String userId = "UID" + (1000 + new Random().nextInt(9000));
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(userForm.getUserImage().getOriginalFilename()));
            Path uploadPath = Paths.get(USER_UPLOAD_DIR);
            System.out.println("User Image UploadPath: " + uploadPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            userForm.getUserImage().transferTo(uploadPath.resolve(fileName).toFile());

            user.setId(userId);
            user.setFullName(userForm.getFirstName() + " " + userForm.getLastName());
            user.setUserImage("/user-images/" + fileName);
            user.setRole(userForm.getRole());
            user.setPassword(userForm.getPassword());
            user.setEnabled(true);
            user.setEmailAddress(userForm.getEmailAddress());
            user.setUsername(userForm.getUsername());
            user.setPhoneNumber(userForm.getPhoneNumber());
            SaveResponse saveResponse = userCommandService.addUser(user);
            if (saveResponse.isSuccess()) {
                redirectAttributes.addFlashAttribute("message", saveResponse.getMessage());
                return "redirect:/userlist";
            }else {
                redirectAttributes.addFlashAttribute("error", saveResponse.getMessage());
                return "redirect:/adduser";
            }
        } catch (IOException e) {
            System.out.println("Exception due to Image in addUser: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to upload product image.");
            return "redirect:/adduser";
        }
    }


}
