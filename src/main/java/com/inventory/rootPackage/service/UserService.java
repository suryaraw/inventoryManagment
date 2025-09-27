package com.inventory.rootPackage.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.UserCredentialEntity;
import com.inventory.rootPackage.model.UserCredentialEntity.Role;
import com.inventory.rootPackage.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder encode;

	@Autowired
	public UserRepository userRepository;

//	public boolean verifyUser(String username, String currentPassword) {
//		// TODO: fetch from DB and validate
//		return "admin".equals(username) && "admin123".equals(currentPassword);
//	}

	public String createUser(UserCredentialEntity user, String confirmPassword) {
//		if (!user.getPassword().equals(confirmPassword)) {
//			return "Passwords do not match!";
//		}
		if (userRepository.existsByUsername(user.getUsername())) {
			return "Username already exists!";
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			return "Email already exists!";
		}
		user.setPassword(passEncode(user.getPassword()));
		userRepository.save(user);
		return "SUCCESS";
	}
	
	public String passEncode(String plain) {
		return encode.encode(plain);
	}

	public boolean isNull() {
		// TODO Auto-generated method stub
		if (userRepository.count() == 0) {
//			System.out.println("row : " + userRepository.count());
			return true;
		}
		return false;
	}

	public void sampleUser() {
		if (isNull() == true) {
			List<UserCredentialEntity> defaultUsers = Arrays.asList(
					new UserCredentialEntity(null, "john_doe", "john@example.com", "9876543210", Role.ADMIN,
							"password123", LocalDateTime.now()),
					new UserCredentialEntity(null, "jane_smith", "jane@example.com", "9876543211", Role.RETAIL_SHOP,
							"password456", LocalDateTime.now()),
					new UserCredentialEntity(null, "alice_wong", "alice@example.com", "9876543212", Role.ADMIN,
							"password789", LocalDateTime.now()));
			userRepository.saveAll(defaultUsers);
		}
	}
	
	public Boolean checkUser(String name) {
		return userRepository.existsByUsername(name);
	}
	
	public UserCredentialEntity finduser(String name) {
		return userRepository.findByUsername(name).get();
	}
	
	public void saveUser(UserCredentialEntity user) {
		userRepository.save(user);
	}
}
