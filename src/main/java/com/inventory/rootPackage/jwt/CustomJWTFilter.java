package com.inventory.rootPackage.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

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
	
//	@Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String path = request.getRequestURI();
//
//        // Debugging help: uncomment if needed
//        // System.out.println("shouldNotFilter check for: " + path);
//
//        if (path.startsWith(request.getContextPath() + "/api/ai")) return true;
//        if (path.startsWith(request.getContextPath() + "/css")
//                || path.startsWith(request.getContextPath() + "/js")
//                || path.startsWith(request.getContextPath() + "/images")
//                || path.startsWith(request.getContextPath() + "/fonts")
//                || path.startsWith(request.getContextPath() + "/favicon")) return true;
//
//        if (path.equals(request.getContextPath() + "/login")
//                || path.equals(request.getContextPath() + "/doLogin")
//                || path.startsWith(request.getContextPath() + "/signup")
//                || path.startsWith(request.getContextPath() + "/createAccount")
//                || path.startsWith(request.getContextPath() + "/forgot-password")
//                || path.startsWith(request.getContextPath() + "/validateOtpAndReset")) {
//            return true;
//        }
//        return false;
//    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String token = null;
//
//        // Read token from cookie
//        if (request.getCookies() != null) {
//            Optional<Cookie> jwtCookie = Arrays.stream(request.getCookies())
//                    .filter(c -> "jwt".equals(c.getName()))
//                    .findFirst();
//            if (jwtCookie.isPresent()) token = jwtCookie.get().getValue();
//        }
//
//        try {
//            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                String username = jwtUtility.extractUsername(token);
//                UserDetails user = userService.loadUserByUsername(username);
//
//                if (jwtUtility.validateToken(token, user.getUsername())) {
//                    var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                } else {
//                    // Clear invalid cookie to prevent repeated failed validations / redirects
//                    Cookie empty = new Cookie("jwt", "");
//                    empty.setMaxAge(0);
//                    empty.setPath("/");
//                    response.addCookie(empty);
//                    System.out.println("Cleared invalid JWT cookie");
//                }
//            }
//        } catch (Exception e) {
//            // If token parsing/validation throws (signature mismatch / expired), clear cookie and continue
//            System.out.println("JWT Validation Failed: " + e.getMessage());
//            Cookie empty = new Cookie("jwt", "");
//            empty.setMaxAge(0);
//            empty.setPath("/");
//            response.addCookie(empty);
//        }
//
//        filterChain.doFilter(request, response);
//    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	        String token = null;
//
//	        // Read from cookie
//	        if (request.getCookies() != null) {
//	            for (Cookie cookie : request.getCookies()) {
//	                if (cookie.getName().equals("jwt")) {
//	                    token = cookie.getValue();
//	                }
//	            }
//	        }
//
//	        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//	            String username = jwtUtility.extractUsername(token);
//	             UserDetails user = userService.loadUserByUsername(username);
//
//	            if (jwtUtility.validateToken(token, user.getUsername())) {
//	                var auth = new UsernamePasswordAuthenticationToken(
//	                        user, null, user.getAuthorities());
//	                SecurityContextHolder.getContext().setAuthentication(auth);
//	            }
//	        }
//
//	        filterChain.doFilter(request, response);
//	    }
//		
//	String token = null;

    try {
        // Extract JWT from cookie
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

    } catch (Exception e) {
        System.out.println("JWT Failed: " + e.getMessage());

        // DELETE BAD COOKIE
        Cookie delete = new Cookie("jwt", null);
        delete.setPath("/");
        delete.setMaxAge(0);
        response.addCookie(delete);
    }

    filterChain.doFilter(request, response);
}
		

}
