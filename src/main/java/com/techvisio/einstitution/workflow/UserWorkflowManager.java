package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.User;

@Component
@Transactional
public interface UserWorkflowManager {

	public void addUser(User user);

	User getUser(Long userId);

	List<Role> getUserRole(Long userId);

}
