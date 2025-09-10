package com.inventory.rootPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class AuthController {

	@GetMapping("/")
    public String loginPage() {
        return "login"; // login.jsp
    }	

    // Handle login form submit
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String role,
                        HttpSession session,
                        Model model) {
        // Simple hardcoded validation
        if ("admin".equals(username) && "admin123".equals(password) && "ADMIN".equals(role)) {
            session.setAttribute("user", username);
            session.setAttribute("role", role);
            System.out.println(username + " " + password);
            return "redirect:/dashboard";
        }
        
        else if ("retail".equals(username) && "retail123".equals(password) && "RETAIL_SHOP".equals(role)) {
            session.setAttribute("user", username);
            session.setAttribute("role", role);
            return "redirect:/retailDashboard";
        }
        
        else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    
 // Show dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", session.getAttribute("user"));
        return "dashboard"; // dashboard.jsp
    }
    
 // Show retail shop dashboard
    @GetMapping("/retailDashboard")
    public String retaildashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", session.getAttribute("user"));
        return "retailDashboard";
    }
    
 // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout"; // logout.jsp
    }

	
}
