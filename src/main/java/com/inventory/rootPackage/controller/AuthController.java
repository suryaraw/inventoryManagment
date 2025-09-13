package com.inventory.rootPackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inventory.rootPackage.service.ItemAddService;
import com.inventory.rootPackage.service.SupplierService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private SupplierService service;

    @Autowired
    private ItemAddService itemservice;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.jsp
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String role,
                        HttpSession session,
                        Model model) {

        if ("admin".equals(username) && "admin123".equals(password) && "ADMIN".equals(role)) {
            session.setAttribute("user", username);
            session.setAttribute("role", role);
            service.addSupplier();
            itemservice.addSampleItems();
            return "redirect:/dashboard";
        } else if ("retail".equals(username) && "retail123".equals(password) && "RETAIL_SHOP".equals(role)) {
            session.setAttribute("user", username);
            session.setAttribute("role", role);
            return "redirect:/shop/items";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", session.getAttribute("user"));
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    // ========== NEW FEATURES ==========

    // Handle Forgot Password
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {
        // TODO: Add your email check + reset password logic
        model.addAttribute("message", "If this email exists, reset link sent to: " + email);
        return "login"; // redirect back to login page with message
    }

    // Handle Create Account
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String email,
                         @RequestParam String password,
                         Model model) {
        // TODO: Save user in DB
        model.addAttribute("message", "Account created successfully! You can login now.");
        return "login";
    }
}
