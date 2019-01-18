package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.myfinishproject.model.Peca;

public class PecaDao extends GenericDao<Peca, Serializable> {

	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Peca> listar() {
		String hql = "select f from Peca f left join fetch f.produto e";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Peca> userList = query.list();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Peca> alterar(Integer id) {
		String hql = "select p from Peca p left join fetch p.produto pr "
				+ "where pr.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Peca> user =  query.list();
		return user;
	}

}
