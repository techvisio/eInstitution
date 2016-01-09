package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.SecurityQuestion;
import com.techvisio.einstitution.beans.User;
import com.techvisio.einstitution.manager.UserManager;
import com.techvisio.einstitution.workflow.UserWorkflowManager;

@Component
@Transactional
public class UserWorkflowManagerImpl implements UserWorkflowManager {

	@Autowired
	UserManager userManager;

	@Override
	public void addUser(User user) {
         userManager.addUser(user);
	}

	@Override
	public User getUser(Long userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	@Override
	public List<Role> getUserRole(Long userId) {
		
		List<Role> userRoles = userManager.getUserRole(userId);
		return userRoles;
	}
	
	@Override
	public void saveSecurityQuestion(SecurityQuestion securityQuestion){
	    userManager.saveSecurityQuestion(securityQuestion);	
	}
	
	@Override
	public SecurityQuestion getSecurityQuestion(Long questionId){
		SecurityQuestion securityQuestion = userManager.getSecurityQuestion(questionId);
		return securityQuestion;
	}
}
