package com.jcwx.game.core.base.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

/**
 * base dao
 * 
 * @author ethan
 */
public class BaseDAO<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
	// TODO Auto-generated method stub
	super.setSessionFactory(sessionFactory);
    }
}
