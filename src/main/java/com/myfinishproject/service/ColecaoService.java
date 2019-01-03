package com.myfinishproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.dao.ColecaoDao;
import com.myfinishproject.model.Colecao;

@Service
public class ColecaoService implements IColecaoService {
	
	private ColecaoDao colecaoDao;
	
	
	public void setColecaoDao(ColecaoDao colecaoDao) {
		this.colecaoDao = colecaoDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Colecao colecao) {
		colecaoDao.SalvarOuAlterar(colecao);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Colecao buscarPorId(Integer id) {
		return colecaoDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer idColecao) {
		colecaoDao.excluir(idColecao);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Colecao> listar() {
		return colecaoDao.listar();
	}
	
	public List<Colecao> search(Search search) {
		return colecaoDao.searchDao(search);

	}

}
