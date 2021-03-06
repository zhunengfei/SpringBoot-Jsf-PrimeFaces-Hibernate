package org.ryi.sbjph.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

// Generated 5 oct. 2017 21:41:51 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SysUserRoles generated by hbm2java
 */
@Entity
@Table(name = "sys_roles",
uniqueConstraints = 
@UniqueConstraint(columnNames = { "user_role_id", "role" }))
public class SysRoles {

	private Integer userRoleId;
	private String role;
	private Set<SysUsers> sysUsers = new HashSet<SysUsers>(0);

	public SysRoles() {
	}

	public SysRoles(Set<SysUsers> sysUsers, String role) {
		this.sysUsers = sysUsers;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_role_id", unique = true, nullable = false)
	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	@JoinColumn(name = "username", nullable = false)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "sys_user_role", joinColumns = {
			@JoinColumn(name = "userRoleId", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "username",
					nullable = false, updatable = false) })
	public Set<SysUsers> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUsers> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
