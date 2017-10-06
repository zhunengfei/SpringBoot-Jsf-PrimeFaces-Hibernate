package org.ryi.sbjph.controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.ryi.sbjph.model.SysUsers;
import org.ryi.sbjph.persistence.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component (value = "listUsers")
@ELBeanName(value = "listUsers")
@Join(path = "/userlist", to = "/user/user-list.jsf")
public class ListUsersController {
	
	@Autowired
    private Dao dao;


	private List<SysUsers> users;

	@Deferred
	@RequestAction
	@IgnorePostback
	@Transactional(readOnly = true)
	public void loadData() {
		users = dao.getListFromQuery("SysUsers.findAllUsers");
	}

	public List<SysUsers> getUsers() {
		return users;
	}

	public String delete(SysUsers user) {
		dao.delete(user);
		loadData();
		return null;
	}
}
