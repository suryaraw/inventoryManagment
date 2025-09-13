package com.inventory.rootPackage.forgotControler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotControl {

	@GetMapping("/forgotPassword")
	public String forgotPage() {
		return "forgotPassword";
	}

}
