package com.techvisio.einstitution.db.impl;

import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Privilege;
import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.User;
import com.techvisio.einstitution.db.SecurityDao;

@Transactional
@Component
public class SecurityDaoImpl extends BaseDao implements SecurityDao {

	@Override
	public User getUserByName(String name) {
		String queryString = "FROM User u WHERE u.name = '" + name+"'";
		Query query = getCurrentSession().createQuery(queryString);
		User result = (User) query.uniqueResult();
		return result;
	}

	@Override
	public Set<Role> getUserRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Privilege> getUserPrivilege() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

}
