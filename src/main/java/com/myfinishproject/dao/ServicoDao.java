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
}
