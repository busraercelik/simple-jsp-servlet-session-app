package bsr.service;

import java.util.HashMap;

import bsr.model.User;

public class LoginService {
	
	HashMap<String, String> users = new HashMap<>();
	
	public LoginService() {
		users.put("bercelik", "123");
		users.put("smandal", "123");
		users.put("emusk", "123");
	}
	
	public boolean authenticate (String username, String password) {
		if (password == null || password.trim()=="") {
			return false;
		}
		String enteredPass = users.get(username);
	    if (!enteredPass.equals(password)) {
			return false;
		}
		return true;
	}
	
	public User getUserDetails (String username) {
		User user = new User();
		user.setUsername(username);
		
		return user;
	}
}
