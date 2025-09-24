package com.inventory.rootPackage.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.rootPackage.model.UserCredentialEntity;
import com.inventory.rootPackage.model.UserCredentialEntity.Role;
import com.inventory.rootPackage.service.ItemAddService;
import com.inventory.rootPackage.service.SupplierService;
import com.inventory.rootPackage.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {


	@Autowired
	private UserService userService;

	@Autowired
	private SupplierService service;

	@Autowired
	private ItemAddService itemservice;

	@GetMapping("/login")
	public String loginPage() {
		userService.sampleUser();
		return "login"; // login.jsp
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, @RequestParam String role,
			HttpSession session, Model model) {

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
	@GetMapping("/forgotPassword")
	public String showForgotPasswordPage() {
		return "forgotPassword"; // forgotPassword.jsp
	}

	// Handle Create Account
	@GetMapping("/createAccount")
	public String showCreateAccountPage() {
		return "createAccount"; // createAccount.jsp
	}

	@PostMapping("/createAccountService")
	public String createAccount(@RequestParam String username, @RequestParam String email, @RequestParam String phone,
			@RequestParam Role role, @RequestParam String password, @RequestParam String confirmPassword, Model model) {

		UserCredentialEntity user = new UserCredentialEntity();
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRole(role);
		user.setPassword(password);

		String result = userService.createUser(user, confirmPassword);

		if ("SUCCESS".equals(result)) {
			return "accountCreated"; // JSP page showing confirmation
		} else {
			model.addAttribute("error", result);
			return "accountNotCreated"; // Back to form with error message
		}
	}

	@PostMapping("/resetPassword")
	public String verifyUser(@RequestParam("username") String username,
			@RequestParam("currentPassword") String currentPassword, Model model) {

		boolean isValid = userService.verifyUser(username, currentPassword);

		if (isValid) {
			model.addAttribute("message", "User verified! You can now reset your password.");
			return "actualRestPage"; // JSP page to reset new password
		} else {
			model.addAttribute("error", "Invalid username or current password.");
			return "forgotPassword"; // back to forgotPassword.jsp
		}
	}

	@PostMapping("/reset")
	public String actualResetPassword(@RequestParam String newPassword, @RequestParam String confirmPassword,
			Model model) {

		System.out.println("New password : " + newPassword);
		// You can add validation and service call here
		return "reset2";
	}

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
    
    @GetMapping("/forgotPassword")
    public String forgotPasswordPage() {
        return "forgotPassword"; // Spring will map to /WEB-INF/jsp/forgotPassword.jsp
    }

    @GetMapping("/createAccount")
    public String createAccountPage() {
        return "createAccount"; // Spring will map to /WEB-INF/jsp/createAccount.jsp
    }
}
