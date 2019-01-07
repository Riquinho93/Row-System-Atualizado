package com.myfinishproject.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.dao.ProdutoDao;
import com.myfinishproject.model.Produto;

public class ProdutoService implements IProdutoService {

	private ProdutoDao produtoDao;

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Produto produto) {
		produtoDao.SalvarOuAlterar(produto);
	}

	@Override
	@Transactional(readOnly = true)
	public Produto buscarPorid(Integer id) {
		return produtoDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		produtoDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> listar() {
		return produtoDao.listar();
	}

	@Override
	public List<Produto> search(Search search) {
		return produtoDao.searchDao(search);
	}

}
