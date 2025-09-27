package com.inventory.rootPackage.springservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventory.rootPackage.model.UserCredentialEntity;
import com.inventory.rootPackage.repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  UserCredentialEntity credential = repo.findByUsername(username)
				  .orElseThrow(()-> new UsernameNotFoundException("User not found "+username));
		  
		return new UserPrincipal(credential);
	}

}
