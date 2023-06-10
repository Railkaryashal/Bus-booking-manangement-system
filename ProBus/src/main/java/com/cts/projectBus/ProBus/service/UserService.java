package com.cts.projectBus.ProBus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.projectBus.ProBus.entity.User;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User getUserByEmail(String email) throws UserNotFoundException {
		User user=userRepository.getUserById(email);
		if(user==null)
			throw new UserNotFoundException("Email id incorrect, retry with correct email address!!");
		return user;
	}
	
}
