package com.myfinishproject.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myfinishproject.model.Funcionario;

@Repository
public class FuncionarioDao extends GenericDao<Funcionario, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Funcionario> listar() {
		Query query = getSessionFactory().getCurrentSession().createQuery("select c from Funcionario c order by c.nome");
		List<Funcionario> userList = query.list();
		return userList;
	}
}
