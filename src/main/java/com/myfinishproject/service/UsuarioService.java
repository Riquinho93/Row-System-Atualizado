package com.myfinishproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.dao.UsuarioDao;
import com.myfinishproject.model.Usuario;

@Service
public class UsuarioService implements IUsuarioService{
	
	private UsuarioDao usuarioDao;
	
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Usuario usuario) {
		usuarioDao.SalvarOuAlterar(usuario);		
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorid(Integer id) {
		return usuarioDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		usuarioDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		return usuarioDao.listar();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> search(Search search) {
		return usuarioDao.searchDao(search);
	}

}
