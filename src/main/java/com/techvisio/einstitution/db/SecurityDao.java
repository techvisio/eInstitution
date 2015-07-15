package com.techvisio.einstitution.db;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Privilege;
import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.User;

@Component
public interface SecurityDao {

	User getUserByName(String name);

	Set<Role> getUserRoles();

	Set<Privilege> getUserPrivilege();

	void addUser(User user);

}
