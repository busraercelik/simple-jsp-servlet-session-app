package bsr.repo;

import bsr.model.User;

public interface UserRepo {
	public User readAllUser();
	public void insertUser(User user);
	public User getUserByUsername(String username);
}
