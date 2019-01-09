package com.myfinishproject.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.myfinishproject.dao.PecaDao;
import com.myfinishproject.model.Peca;

public class PecaService implements IPecaService {

	private PecaDao pecaDao;

	public void setPecaDao(PecaDao pecaDao) {
		this.pecaDao = pecaDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Peca peca) {
		pecaDao.SalvarOuAlterar(peca);
	}

	@Override
	@Transactional(readOnly = true)
	public Peca buscarPorId(Integer id) {
		return pecaDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		pecaDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Peca> listar() {
		return pecaDao.listar();
	}

}
