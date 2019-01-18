package com.myfinishproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myfinishproject.dao.MaterialDao;
import com.myfinishproject.model.Material;

@Service
public class MaterialService implements IMaterialService {

	private MaterialDao materialDao;

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Material material) {
		materialDao.SalvarOuAlterar(material);
	}

	@Override
	@Transactional(readOnly = true)
	public Material buscarPorId(Integer id) {
		return materialDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		materialDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> listar() {
		return materialDao.listar();
	}
	
	@Transactional(readOnly = true)
	public List<Material> alterar(Integer id){
		return materialDao.alterar(id);
	}


}
