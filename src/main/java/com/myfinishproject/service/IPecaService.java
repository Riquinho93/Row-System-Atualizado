package com.myfinishproject.service;

import java.util.List;

import com.myfinishproject.model.Peca;

public interface IPecaService {
	
	public void SalvarOuAlterar(Peca peca);

	public Peca buscarPorId(Integer id);

	public void excluir(Integer id);

	public List<Peca> listar();
}
