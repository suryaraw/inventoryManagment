package com.inventory.rootPackage.controller;



import java.security.Principal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.rootPackage.mailService.MailService;
import com.inventory.rootPackage.model.UserCredentialEntity;
import com.inventory.rootPackage.model.UserCredentialEntity.Role;
import com.inventory.rootPackage.service.ItemAddService;
import com.inventory.rootPackage.service.SupplierService;
import com.inventory.rootPackage.service.UserService;
import com.inventory.rootPackage.springservice.CustomUserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {


	@Autowired
	private UserService userService;

	@Autowired
	private SupplierService service;

	@Autowired
	private ItemAddService itemservice;
	
	@Autowired
	private AuthenticationManager  authenticationManager;
	
	@Autowired
	private CustomUserService userservice;
	
	@Autowired
	private MailService mail;
	
	private Integer otp=100000;

	@GetMapping("/login")
	public String loginPage() {
		System.out.println("login");
		return "login";
	}
	
//	@PostMapping("/doLogin")
//	public String doLogin(
//	        @RequestParam String username,
//	        @RequestParam String password,
//	        HttpServletRequest request) {
//
//	    try {
//	        // Create the authentication token
//	        UsernamePasswordAuthenticationToken authToken =
//	                new UsernamePasswordAuthenticationToken(username, password);
//
//	        Authentication authenticate = authenticationManager.authenticate(authToken);
//	        if(authenticate.isAuthenticated()) {
//	        		String role = userservice.loadUserByUsername(username).getAuthorities().iterator().next().getAuthority();
//	        		System.out.println(role);
//	        		if (role.equals("ROLE_ADMIN")) {
//	                    return "redirect:/dashboard";
//	                }
//	        		else if (role.equals("ROLE_RETAIL_SHOP")) {
//	                	return "redirect:/shop/items";
//	                }
//	        }
//	        return "redirect:/login";
//	    } catch (AuthenticationException e) {
//	        return "redirect:/login";
//	    }
//	}


	@GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println(role);
        if (role.equals("ROLE_ADMIN")) {
            return "dashboard"; // JSP: /WEB-INF/views/adminDashboard.jsp
        } else if (role.equals("ROLE_RETAIL_SHOP")) {
            return "redirect:/shop/items"; // JSP: /WEB-INF/views/retailDashboard.jsp
        }
        return "accessDenied";
    }



	@GetMapping("/logout")
	public String logout() {
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
			@RequestParam("currentPassword") String currentPassword, Model model) throws MessagingException {

//		boolean isValid = userService.verifyUser(username, currentPassword);

		if (userService.checkUser(username)) {
			otp = 100000;
			String mailsent =userService.finduser(username).getEmail();
			otp = new Random().nextInt(900000)+otp;
			mail.sendEmail(mailsent, "Otp for password change", String.valueOf(otp));
			model.addAttribute("message", "User verified! Enter OTP sent to "+ mailsent);
			model.addAttribute("username",username);
			model.addAttribute("password",currentPassword);
			return "otp"; // JSP page to reset new password
		} else {
			model.addAttribute("error", "Invalid username or current password.");
			return "forgotPassword"; // back to forgotPassword.jsp
		}
	}
	
	@PostMapping("/validateOtpAndReset")
	public String validateOtpAndReset(
	        @RequestParam("username") String username,
	        @RequestParam("otp") String otpEntered,
	        @RequestParam("newpass") String newPassword,
	        Model model) {
		System.out.println("--------------------");
		System.out.println("otp : " + otpEntered);
		System.out.println(username +" "+newPassword);
		if(Integer.parseInt(otpEntered)==otp) {
			UserCredentialEntity user = userService.finduser(username);
			user.setPassword(userService.passEncode(newPassword));
			userService.saveUser(user);
			return "reset2";
		}
		else {
			return "login";
		}
	}

//	@PostMapping("/reset")
//	public String actualResetPassword(@RequestParam String newPassword, @RequestParam String confirmPassword,
//			Model model) {
//
//		System.out.println("New password : " + newPassword);
//		// You can add validation and service call here
//		return "reset2";
//	}

    // Handle Forgot Password
    
    
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {
        model.addAttribute("message", "If this email exists, reset link sent to: " + email);
        return "login"; // redirect back to login page with message
    }

    // Handle Create Account
//    @PostMapping("/signup")
//    public String signup(@RequestParam String username,
//                         @RequestParam String email,
//                         @RequestParam String password,
//                         Model model) {
//        // TODO: Save user in DB
//        model.addAttribute("message", "Account created successfully! You can login now.");
//        return "login";
//    }
    
}
