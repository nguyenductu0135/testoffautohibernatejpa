package edu.poly.spring.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.spring.entity.Category;



@Transactional
@Repository
public class CategoryDAOimpl implements CategoryDAO{
	@Autowired
	SessionFactory factory;
	@Override
	public Category findId(String id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		return session.find(Category.class, id);
	}


	@Override
	public List<Category> danhsach() {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		TypedQuery<Category> query = session.createQuery("from Category", Category.class);
		return query.getResultList();
	}

	@Override
	public Category them(Category entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void capnhat(Category entity) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public List<Category> danhsach(int id) {
		Session session = factory.getCurrentSession();
		TypedQuery<Category> query = session.createQuery("from Category where id='" + id + "'", Category.class);
		return query.getResultList();
	}
	


}
