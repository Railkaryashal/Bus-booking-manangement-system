package com.cts.projectBus.ProBus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.projectBus.ProBus.entity.User;

public interface UserRepository extends JpaRepository<User,String>{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User getUserById(String email);
}
