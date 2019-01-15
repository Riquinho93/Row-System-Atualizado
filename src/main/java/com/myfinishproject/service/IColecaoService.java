package com.myfinishproject.service;

import java.util.List;

import com.myfinishproject.model.Colecao;


public interface IColecaoService {
	
	public List<String> SalvarOuAlterar(Colecao colecao);

	public Colecao buscarPorId(Integer id);

	public void excluir(Integer idColecao);
	
	public List<Colecao> listar();
	
}
