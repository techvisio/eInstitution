package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.User;
import com.techvisio.einstitution.db.SecurityDao;
import com.techvisio.einstitution.db.UserDao;
import com.techvisio.einstitution.manager.UserManager;

public class UserManagerImpl implements UserManager{

	@Autowired
	UserDao userDao;
	
	@Override
	public void addUser(User user) {
      userDao.addUser(user);		
	}

	@Override
	public User getUser(Long userId) {
		User user = userDao.getUser(userId);
		return user;
	}

	@Override
	public List<Role> getUserRole(Long userId) {
		
		List<Role> userRoles = userDao.getUserRole(userId);
		return userRoles;
	}	
}
