 package bsr.service;

import java.util.HashMap;

import bsr.model.User;
import bsr.repo.UserRepo;
import bsr.repo.impl.UserRepoImpl;

public class LoginService {
	
	UserRepo userRepo=null;
	
	public LoginService() {
		userRepo= new UserRepoImpl();
	}
	
	public boolean authenticate (String enteredUsername, String enteredPassword) {
		User user = null;
		
		if (enteredPassword == null || enteredPassword.trim().equals("")) {
			return false;
		}
		user = userRepo.getUserByUsername(enteredUsername);
		if (user == null) {
			return false;
		}
		String passwordFromDb = user.getPassword();
	    if (!enteredPassword.equals(passwordFromDb)) {
			return false;
		} 
		return true;
	}

	public User getUserDetails(String enteredUsername) {
		return userRepo.getUserByUsername(enteredUsername);
	}
	
	
}
