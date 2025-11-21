package com.inventory.rootPackage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.inventory.rootPackage.jwt.CustomJWTFilter;
import com.inventory.rootPackage.jwt.customSucessHandler;
import com.inventory.rootPackage.oauth2.OAuth2MvcSuccessHandler;
import com.inventory.rootPackage.springservice.CustomUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserService service;
	
//	@Autowired
//	private customSucessHandler handler;
//	
//	@Autowired
//	private CustomJWTFilter filter;
//	
//	@Autowired
//	private  OAuth2MvcSuccessHandler oAuth2MvcSuccessHandler;
	
	
//	@Bean
//	public SecurityFilterChain filterChain(
//	        HttpSecurity http,
//	        OAuth2MvcSuccessHandler oAuth2MvcSuccessHandler,
//	        customSucessHandler handler,
//	        CustomJWTFilter filter) throws Exception {
//
//	    http
//	        .csrf(csrf -> csrf.disable())
//	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	        .authorizeHttpRequests(auth -> auth
//
//	            // static
//	            .requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico", "/webjars/**", "/fonts/**", "/font/**").permitAll()
//
//	            // public pages & auth endpoints
//	            .requestMatchers("/login", "/doLogin", "/logout", "/createAccount", "/forgotPassword",
//	                             "/signup/**", "/forgot-password/**", "/validateOtpAndReset", "/resetPassword",
//	                             "/createAccountService").permitAll()
//
//	            // AI endpoint open
//	            .requestMatchers("/api/ai/**").permitAll()
//
//	            // role protected
//	            .requestMatchers("/admin/**", "/items/**", "/item/**", "/approve/**", "/sendFailureMail/**",
//	                             "/orders/**", "/notifyBrand/**", "/report/**", "/sup/**").hasRole("ADMIN")
//
//	            .requestMatchers("/shop/**", "/checkout", "/paymentSuccess").hasRole("RETAIL_SHOP")
//
//	            // dashboard - if you want dashboard public to authenticated users, use authenticated()
//	            .requestMatchers("/dashboard").authenticated()
//
//	            .anyRequest().authenticated()
//	        )
//	        .formLogin(form -> form
//	            .loginPage("/login")
//	            .loginProcessingUrl("/doLogin")
//	            .usernameParameter("username")
//	            .passwordParameter("password")
//	            .successHandler(handler)
//	            .failureUrl("/login?error=true")
//	            .permitAll()
//	        )
//	        .oauth2Login(oauth2 -> oauth2
//	            .loginPage("/login")
//	            .successHandler(oAuth2MvcSuccessHandler)
//	        )
//	        .logout(logout -> logout
//	            .logoutUrl("/logout")
//	            .deleteCookies("jwt")
//	            .clearAuthentication(true)
//	            .logoutSuccessUrl("/login?logout=true")
//	            .permitAll()
//	        );
//
//	    // make sure JWT filter executes BEFORE UsernamePasswordAuthenticationFilter
//	    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//
//	    return http.build();
//	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, 
            OAuth2MvcSuccessHandler oAuth2MvcSuccessHandler,
            customSucessHandler handler,
            CustomJWTFilter filter) throws Exception {
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // keep disabled for now
	        .sessionManagement(session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 🔥 Add this line
        )
	        .authorizeHttpRequests(auth -> auth
	            // Public pages
	            .requestMatchers(HttpMethod.GET,"/login", "/createAccount", "/forgotPassword","/pinecone/**","/ai/**").permitAll()
	            .requestMatchers("/images/**", "/css/**", "/js/**", "/webjars/**","/WEB-INF/**",
	            
	                    "/favicon.ico",
	                    "/fonts/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/doLogin","/signup/**","/forgot-password/**","/validateOtpAndReset","/resetPassword","/createAccountService","/api/ai/**").permitAll()
	            
	            // Admin-only pages
	            .requestMatchers("/admin/**", "/items/**", "/item/**", "/approve/**", "/sendFailureMail/**", "/orders/**", "/notifyBrand/**", "/report/**", "/sup/**").hasRole("ADMIN")
	            
	            // Retail Shop pages
	            .requestMatchers("/shop/**", "/checkout", "/paymentSuccess").hasRole("RETAIL_SHOP")
	            
	            // Everything else needs authentication
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .loginProcessingUrl("/doLogin")
	            .usernameParameter("username")
	            .passwordParameter("password")
//	            .defaultSuccessUrl("/dashboard", true)
	            .successHandler(handler)
	            .failureUrl("/login?error=true")
	            .permitAll()
	        )
	        .oauth2Login(oauth2 -> oauth2
	                .loginPage("/login") // show your JSP login page
	                .successHandler(oAuth2MvcSuccessHandler)
//	                .defaultSuccessUrl("/dashboard", true) // redirect after successful OAuth2 login
	               
	                    // Optional: custom OAuth2UserService if you want DB mapping
	                
	            )
	        .logout(logout -> logout
	        	    .logoutUrl("/logout")   
	        	    .logoutSuccessUrl("/login") // GET or POST /logout
	        	    .deleteCookies("jwt")                // delete JWT cookie
	        	    .clearAuthentication(true)
	        	    .logoutSuccessUrl("/login?logout=true")
	        	    .permitAll()
	        );
	    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	
	
	@Bean
	PasswordEncoder pwdencode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authman(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	AuthenticationProvider authpro() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(pwdencode());
		provider.setUserDetailsService(service);
		return provider;
	}

	
//	@Bean
//	public UserDetailsService userDetailsService() {
//	    UserDetails admin = User.builder()
//	        .username("admin")
//	        .password(pwdencode().encode("admin123"))
//	        .roles("ADMIN")
//	        .build();
//
//	    UserDetails retail = User.builder()
//	        .username("retail")
//	        .password(pwdencode().encode("retail123"))
//	        .roles("RETAIL_SHOP")
//	        .build();
//
//	    return new InMemoryUserDetailsManager(admin, retail);
//	}

}
