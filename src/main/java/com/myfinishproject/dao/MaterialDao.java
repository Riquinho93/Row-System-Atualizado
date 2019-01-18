package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myfinishproject.model.Material;

@Repository
public class MaterialDao extends GenericDao<Material, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Material> listar() {
		String hql = "select f from Material f left join fetch f.produto e";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Material> userList = query.list();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Material> alterar(Integer id) {
		String hql = "select m from Material m left join fetch m.produto pr "
				+ "where pr.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Material> user =  query.list();
		return user;
	}
}
