package com.inventory.rootPackage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.rootPackage.model.UserCredentialEntity;

@Repository
public interface UserRepository extends JpaRepository<UserCredentialEntity, Integer> {
	 boolean existsByUsername(String username);
	    boolean existsByEmail(String email);
	    Optional<UserCredentialEntity> findByUsername(String name) ;
	    
	    @Query(value = "select * from users where role = :role",nativeQuery = true)
	    List<UserCredentialEntity> allAdmin(@Param("role") String  role);
}
