package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myfinishproject.model.Colecao;

@Repository
public class ColecaoDao extends GenericDao<Colecao, Serializable> {
	
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Colecao> listar() {
		Query query = getSessionFactory().getCurrentSession().createQuery("select c from Colecao c order by c.nome");
		List<Colecao> userList = query.list();
		return userList;
	}
}
