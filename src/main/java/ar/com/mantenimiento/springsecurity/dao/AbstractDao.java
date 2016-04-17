package ar.com.mantenimiento.springsecurity.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	@SuppressWarnings({ "hiding", "unchecked" })
	public <T> List<T> getAll(Class<T> clazz) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(clazz);
		return criteria.list();
	}

	public void persist(T entity) {
		Session session = getSession();
		session.persist(entity);
		session.flush();
		
		
	}
	
	
	public void update(T entity) {
		Session session = getSession();
		session.update(entity);
		session.flush();
		
		
	}

	public void delete(T entity) {
		Session session = getSession();
		session.delete(entity);
		session.flush();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}
