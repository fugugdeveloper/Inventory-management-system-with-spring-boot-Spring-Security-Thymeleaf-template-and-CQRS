package com.ktun.inventory_management_system.controller.command;

import com.ktun.inventory_management_system.helper.JwtUtil;
import com.ktun.inventory_management_system.model.AuthRequest;
import com.ktun.inventory_management_system.model.User;
import com.ktun.inventory_management_system.repository.TokenRepository;
import com.ktun.inventory_management_system.repository.UserRepository;
import com.ktun.inventory_management_system.service.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class AuthCommandController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login") // when trying this url,select auth type: No Auth
    public String loginCommand(Model m,
                        HttpSession session,
                        @ModelAttribute AuthRequest authRequest,
                        HttpServletResponse res,
                        RedirectAttributes attrs) throws Exception
    {
        System.out.println(authRequest);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (UsernameNotFoundException | BadCredentialsException e) {

            session.setAttribute("msg","Bad Credentials");
            attrs.addFlashAttribute("error","Bad Credentials");
            return "redirect:/login";

        }

        // fine area..

        try {

            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());



            System.out.println("userDetails.getUsername: "   +userDetails.getUsername());


            final String token =	jwtUtil.generateToken(userDetails);


            Cookie cookie = new Cookie("token",token);
            cookie.setMaxAge(Integer.MAX_VALUE);
            res.addCookie(cookie);


            System.out.println("token: " + token);



            return "redirect:/";
        }catch(Exception e)
        {
            session.setAttribute("msg","Credentials were right But something went wrong!!");
            attrs.addFlashAttribute("error",e.getMessage());
            return "redirect:/login";
        }
    }
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user, RedirectAttributes attrs) {
        Set<String> roles = new HashSet<>();
        user.setRole("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        attrs.addFlashAttribute("msg", "Registered successfully. Please login.");
        return "redirect:/login";
    }

}
