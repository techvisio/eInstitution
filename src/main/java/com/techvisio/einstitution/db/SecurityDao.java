package com.techvisio.einstitution.db;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Privilege;
import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.User;

@Component
public interface SecurityDao {

	User authenticateNgetUser(Authentication  authentication);

	Set<Privilege> getUserPrivilege(Long userId);

}
