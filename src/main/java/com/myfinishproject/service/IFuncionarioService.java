package com.myfinishproject.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.model.Funcionario;


public interface IFuncionarioService {
	

	public void SalvarOuAlterar(Funcionario funcionario);

	public Funcionario buscarPorId(Integer id);

	public void excluir(Integer idFuncionario);
	
	public List<Funcionario> listar();
	
	public List<Funcionario> search(Search search);
}
