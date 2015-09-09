package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.User;

public interface UserDao {

	public void addUser(User user);

	User getUser(Long userId);

	List<Role> getUserRole(Long userId);

}
