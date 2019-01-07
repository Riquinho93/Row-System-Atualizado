package com.myfinishproject.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.model.Produto;

public interface IProdutoService{
	
	public void SalvarOuAlterar(Produto produto);
	
	public Produto buscarPorid(Integer id);
	
	public void excluir(Integer id);
	
	public List<Produto> listar();
	
	public List<Produto> search(Search search);

}
