package com.myfinishproject.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.model.Servico;

public interface IServicoService {
	
	public void SalvarOuAlterar(Servico servico);
	
	public Servico buscarPorid(Integer id);
	
	public void excluir(Integer id);
	
	public List<Servico> listar();
	
	public List<Servico> search(Search search);
}
