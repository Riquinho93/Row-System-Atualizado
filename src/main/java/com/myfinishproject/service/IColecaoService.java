package com.myfinishproject.service;

import java.util.List;

import com.myfinishproject.model.Colecao;


public interface IColecaoService {
	
	public void SalvarOuAlterar(Colecao colecao);

	public Colecao buscarPorId(Integer id);

	public void excluir(Integer idColecao);
	
	public List<Colecao> listar();
	
}
