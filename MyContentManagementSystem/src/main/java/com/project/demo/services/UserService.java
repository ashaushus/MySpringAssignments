package com.project.demo.services;

import com.project.demo.models.User;

public interface UserService {
	    User register(User user);

	    User findByUsername(String username);

		void saveRegisteredUser(User user);

		User registerNewUserAccount(User user) throws Throwable;
		
		User findUserByEmail(String email);
	}
