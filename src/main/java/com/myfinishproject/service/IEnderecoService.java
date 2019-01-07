package com.myfinishproject.service;

import java.util.List;

import com.myfinishproject.model.Endereco;

public interface IEnderecoService {

	public void SalvarOuAlterar(Endereco endereco);

	public Endereco buscarPorId(Integer id);

	public void excluir(Integer id);

	public List<Endereco> listar();
}
