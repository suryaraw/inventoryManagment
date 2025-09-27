package com.inventory.rootPackage.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inventory.rootPackage.springservice.CustomUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomJWTFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private CustomUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	        String token = null;

	        // Read from cookie
	        if (request.getCookies() != null) {
	            for (Cookie cookie : request.getCookies()) {
	                if (cookie.getName().equals("jwt")) {
	                    token = cookie.getValue();
	                }
	            }
	        }

	        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            String username = jwtUtility.extractUsername(token);
	             UserDetails user = userService.loadUserByUsername(username);

	            if (jwtUtility.validateToken(token, user.getUsername())) {
	                var auth = new UsernamePasswordAuthenticationToken(
	                        user, null, user.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(auth);
	            }
	        }

	        filterChain.doFilter(request, response);
	    }
		
	

}
