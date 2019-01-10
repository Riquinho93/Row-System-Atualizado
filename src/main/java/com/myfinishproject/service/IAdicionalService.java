package com.myfinishproject.service;

import java.util.List;

import com.myfinishproject.model.Adicional;


public interface IAdicionalService {
	
	public void SalvarOuAlterar(Adicional adicional);

	public Adicional buscarPorId(Integer id);

	public void excluir(Integer id);

	public List<Adicional> listar();
}
