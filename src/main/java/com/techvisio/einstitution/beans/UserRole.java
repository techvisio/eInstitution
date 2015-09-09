package com.techvisio.einstitution.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")    
public class UserRole extends BasicEntity{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_Role_Id")
	private Long userRoleId;
	@ManyToOne
	@JoinColumn(name = "Role_Id")
	private Role role;
	@ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.EAGER)
	@JoinColumn(name="Privilege_Id")
	private List<Privilege> privilegeList;
	@Column(name ="User_Id")
	private Long userId;

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
