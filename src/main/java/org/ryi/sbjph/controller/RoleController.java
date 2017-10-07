package org.ryi.sbjph.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ryi.sbjph.model.SysRoles;
import org.ryi.sbjph.persistence.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component (value = "roleController")
@ELBeanName(value = "roleController")
@Join(path = "/roleform", to = "/role/role-form.jsf")
public class RoleController {
	
	@Autowired
    private Dao dao;

	private SysRoles sysRoles = new SysRoles();

	public String save() {
		dao.save(sysRoles);
		sysRoles = new SysRoles();
		return "/role/role-list.xhtml?faces-redirect=true";
	}

	public SysRoles getSysRoles() {
		return sysRoles;
	}
	
	/*public void addMessage() {
        String summary = sysUserRoles.getEnabled() ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }*/
}
