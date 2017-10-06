package org.ryi.sbjph.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ryi.sbjph.model.SysUsers;
import org.ryi.sbjph.persistence.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component (value = "userController")
@ELBeanName(value = "userController")
@Join(path = "/userform", to = "/user/user-form.jsf")
public class UserController {
	
	@Autowired
    private Dao dao;

	private SysUsers sysUsers = new SysUsers();

	public String save() {
		dao.save(sysUsers);
		sysUsers = new SysUsers();
		return "/user/user-list.xhtml?faces-redirect=true";
	}

	public SysUsers getSysUsers() {
		return sysUsers;
	}
	
	public void addMessage() {
        String summary = sysUsers.getEnabled() ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
}
