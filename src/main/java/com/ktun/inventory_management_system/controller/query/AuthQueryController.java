package com.ktun.inventory_management_system.controller.query;

import com.ktun.inventory_management_system.model.AuthRequest;
import com.ktun.inventory_management_system.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AuthQueryController {
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/login")
    public String loginPage( Model model) {
        AuthRequest authRequest = new AuthRequest();
        model.addAttribute("authRequest", authRequest);
        return "signin";
    }
    @GetMapping("/log_out")
    public String logout(HttpServletRequest request, HttpServletResponse res, Model m, HttpSession session) {


        String msg = null;

        Cookie[] cookies2 = request.getCookies();
        for (Cookie cookie : cookies2) {
            if (cookie.getName().equals("token")) {
                cookie.setMaxAge(0);
                res.addCookie(cookie);
                msg = "Logout successfully";

            }

        }
        session.setAttribute("msg", msg);


        return "redirect:/login";

    }
}
