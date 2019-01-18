package com.myfinishproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfinishproject.dao.AdicionalDao;
import com.myfinishproject.model.Adicional;

@Service
public class AdicionalService implements IAdicionalService {

	private AdicionalDao adicionalDao;

	public void setAdicionalDao(AdicionalDao adicionalDao) {
		this.adicionalDao = adicionalDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Adicional adicional) {
		adicionalDao.SalvarOuAlterar(adicional);
	}

	@Override
	@Transactional(readOnly = true)
	public Adicional buscarPorId(Integer id) {
		return adicionalDao.buscarPorId(id);
	}

	@Override
	@Transactional 
	public void excluir(Integer id) {
		adicionalDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Adicional> listar() {
		return adicionalDao.listar();
	}
	
	@Transactional(readOnly = true)
	public List<Adicional> alterar(Integer id){
		return adicionalDao.alterar(id);
	}

}
