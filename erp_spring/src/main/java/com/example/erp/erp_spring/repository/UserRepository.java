package com.example.erp.erp_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erp.erp_spring.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	public UserEntity findByUserEmail(String userEmail);
}
