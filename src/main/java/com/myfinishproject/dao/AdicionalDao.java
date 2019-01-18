package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myfinishproject.model.Adicional;

@Repository
public class AdicionalDao extends GenericDao<Adicional, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Adicional> listar() {
		String hql = "select f from Adicional f left join fetch f.produto e";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Adicional> userList = query.list();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Adicional> alterar(Integer id) {
		String hql = "select a from Adicional a left join fetch a.produto pr "
				+ "where pr.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Adicional> user =  query.list();
		return user;
	}
}
