package com.inventory.rootPackage.forgotControler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotAndCreateControl {

	@GetMapping("/forgotPassword")
	public String forgotPage() {
		return "forgotPassword";
	}
	
	@GetMapping("/createAccount")
	public String createPage() {
		return "createAccount";
	}

}
