package org.ryi.sbjph.controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.ryi.sbjph.model.Product;
import org.ryi.sbjph.persistence.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Scope (value = "session")
@Component (value = "listProducts")
@ELBeanName(value = "listProducts")
@Join(path = "/", to = "/product/product-list.jsf")
public class ListProductsController {
	
	@Autowired
    private Dao productDao;


	private List<Product> products;

	@Deferred
	@RequestAction
	@IgnorePostback
	@Transactional(readOnly = true)
	public void loadData() {
		products = productDao.getAll(Product.class);
	}

	public List<Product> getProducts() {
		return products;
	}

	public String delete(Product product) {
		productDao.delete(product);
		loadData();
		return null;
	}
}
