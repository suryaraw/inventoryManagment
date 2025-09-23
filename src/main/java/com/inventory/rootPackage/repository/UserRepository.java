package com.inventory.rootPackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.rootPackage.model.UserCredentialEntity;

@Repository
public interface UserRepository extends JpaRepository<UserCredentialEntity, Integer> {
	 boolean existsByUsername(String username);
	    boolean existsByEmail(String email);
}
