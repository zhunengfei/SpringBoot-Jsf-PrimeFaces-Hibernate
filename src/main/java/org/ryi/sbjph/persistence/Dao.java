package org.ryi.sbjph.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@SuppressWarnings("unchecked")
@Transactional
public class Dao{

	@Autowired(required=true)
    private SessionFactory sessionFactory;

     public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public <T> T save(final T o){
      return (T) sessionFactory.getCurrentSession().save(o);
    }


    public void delete(Object object){
    	//sessionFactory.getCurrentSession().get(object);
    	object = sessionFactory.getCurrentSession().merge(object);
    	System.out.println("heloooo   ");
    	sessionFactory.getCurrentSession().delete(object);
    }

    /***/
    public <T> T get(final Class<T> type, final Long id){
      return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    /***/
    public <T> T merge(final T o)   {
      return (T) sessionFactory.getCurrentSession().merge(o);
    }

    /***/
    public <T> void saveOrUpdate(final T o){
      sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public <T> List<T> getAll(final Class<T> type) {
      final Session session = sessionFactory.getCurrentSession();
      final Criteria crit = session.createCriteria(type);
      return crit.list();
    }
    
    public <T> List<T> getListFromQuery(String queryName) {
        final Session session = sessionFactory.getCurrentSession();
        List<T> list = session.getNamedQuery(queryName).list();
        return list;
      }
    
}