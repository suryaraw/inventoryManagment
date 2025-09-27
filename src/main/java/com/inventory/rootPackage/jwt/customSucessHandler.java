package com.inventory.rootPackage.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class customSucessHandler  implements AuthenticationSuccessHandler{
	
	@Autowired
    private JwtUtility jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        // Generate JWT
        String token = jwtUtil.generateToken(username, role);

        // Store JWT in cookie
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);// jscript dont read
        cookie.setPath("/");
        response.addCookie(cookie);

        // Redirect by role
        if (role.equals("ROLE_ADMIN")) {
            response.sendRedirect("/dashboard");
        } else if (role.equals("ROLE_RETAIL_SHOP")) {
            response.sendRedirect("/shop/items");
        } else {
            response.sendRedirect("/login");
        }
    }
}
