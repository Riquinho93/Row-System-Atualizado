package com.myfinishproject.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.model.Usuario;

public interface IUsuarioService {

	public void SalvarOuAlterar(Usuario usuario);

	public Usuario buscarPorid(Integer id);

	public void excluir(Integer id);

	public List<Usuario> listar();

	public List<Usuario> search(Search search);
}
