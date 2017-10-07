package org.ryi.sbjph.controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.ryi.sbjph.model.SysRoles;
import org.ryi.sbjph.persistence.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component (value = "listRoles")
@ELBeanName(value = "listRoles")
@Join(path = "/rolelist", to = "/role/role-list.jsf")
public class ListRolesController {
	
	@Autowired
    private Dao dao;

	private List<SysRoles> roles;

	@Deferred
	@RequestAction
	@IgnorePostback
	@Transactional(readOnly = true)
	public void loadData() {
		roles = dao.getAll(SysRoles.class);
	}

	public List<SysRoles> getRoles() {
		return roles;
	}

	public String delete(SysRoles user) {
		dao.delete(user);
		loadData();
		return null;
	}
}
