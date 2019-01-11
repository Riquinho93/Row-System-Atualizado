package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.myfinishproject.model.Produto;

public class ProdutoDao extends GenericDao<Produto, Serializable> {

	@SuppressWarnings("unchecked")
	public List<Produto> listar(Integer id) {
		Query query = getSessionFactory().getCurrentSession().createQuery("select distinct p from Produto p, Colecao c where "+id +" = p.colecao");
		List<Produto> lists = query.list();
		return lists;
	}
	
}
