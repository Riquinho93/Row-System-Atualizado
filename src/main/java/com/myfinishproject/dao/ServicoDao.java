package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myfinishproject.model.Servico;

@Repository
public class ServicoDao extends GenericDao<Servico, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Servico> listar() {
		String hql = "select f from Servico f left join fetch f.produto e";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Servico> userList = query.list();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Servico> alterar(Integer id) {
		String hql = "select s from Servico s left join fetch s.produto pr "
				+ "where pr.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Servico> user =  query.list();
		return user;
	}
}
