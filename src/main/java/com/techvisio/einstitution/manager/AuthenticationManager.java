package com.techvisio.einstitution.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.techvisio.einstitution.beans.Privilege;
import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.User;
import com.techvisio.einstitution.db.SecurityDao;

public class AuthenticationManager implements UserDetailsService {

	@Autowired
	SecurityDao securityDao;

	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		User user = securityDao.getUserByName(name);
		UserDetails userDtls = new org.springframework.security.core.userdetails.User(
				user.getName(), String.valueOf(user.getPassword()), buildAuthoritiesRef(user));
		return userDtls;
	}

	private List<GrantedAuthority> buildAuthoritiesRef(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role userRole : user.getRoles()) {
			for (Privilege userPrivilege : userRole.getPrivilegeList()) {
				authorities.add(new SimpleGrantedAuthority(userPrivilege
						.getPrivilege()));
			}
		}

		return authorities;
	}

}
