package com.inventory.rootPackage.oauth2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.inventory.rootPackage.jwt.JwtUtility;
import com.inventory.rootPackage.model.UserCredentialEntity;
import com.inventory.rootPackage.model.UserCredentialEntity.Role;
import com.inventory.rootPackage.repository.UserRepository;
import com.inventory.rootPackage.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2MvcSuccessHandler implements AuthenticationSuccessHandler {
	
	
	
	private final UserService userservice;
    private final JwtUtility jwtUtil;

    @Autowired
    public OAuth2MvcSuccessHandler(UserService userService, JwtUtility jwtUtil) {
        this.userservice = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Extract info from Google ID token
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Check if user exists in DB
        UserCredentialEntity finduser = userservice.finduser(name);
        if(finduser==null) {
        		UserCredentialEntity user = new UserCredentialEntity();
        		user.setEmail(email);user.setPassword("ByGoogle");
        		user.setRole(Role.RETAIL_SHOP);user.setUsername(name);
        		user.setPhone("9999999999");
        		userservice.saveUser(user);
        }
        
        // Generate JWT for your app
        String token = jwtUtil.generateToken(name, "ROLE_RETAIL_SHOP");

        // Add JWT as cookie (so your MVC controllers can use it via filter)
        response.addHeader("Set-Cookie", "jwt=" + token + "; HttpOnly; Path=/; Max-Age=" + jwtUtil.getJwtExpiration());

        // Redirect to dashboard JSP
        System.out.println("?????????????? google");
        response.sendRedirect(request.getContextPath() + "/shop/items");
    }
}

