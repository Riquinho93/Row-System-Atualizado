package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.myfinishproject.model.Peca;

public class PecaDao extends GenericDao<Peca, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Peca> listar() {
		Query query = getSessionFactory().getCurrentSession().createQuery("select c from Peca c order by c.cor");
		List<Peca> userList = query.list();
		return userList;
	}
}
