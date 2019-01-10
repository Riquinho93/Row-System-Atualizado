package com.myfinishproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.dao.ServicoDao;
import com.myfinishproject.model.Servico;

@Service
public class ServicoService implements IServicoService {

	private ServicoDao servicoDao;

	public void setServicoDao(ServicoDao servicoDao) {
		this.servicoDao = servicoDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Servico servico) {
		servicoDao.SalvarOuAlterar(servico);
	}

	@Override
	@Transactional(readOnly = true)
	public Servico buscarPorid(Integer id) {
		return servicoDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		servicoDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servico> listar(Integer id) {
		return servicoDao.listar();
	}

	@Override
	public List<Servico> search(Search search) {
		return servicoDao.searchDao(search);
	}

}
