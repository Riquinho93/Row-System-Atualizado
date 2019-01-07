package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.myfinishproject.model.Produto;

public class ProdutoDao extends GenericDao<Produto, Serializable> {

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		Query query = getSessionFactory().getCurrentSession().createQuery("select c from Produto order by c.modelo");
		List<Produto> lists = query.list();
		return lists;
	}
}
