package com.myfinishproject.service;

import java.util.List;

import com.myfinishproject.model.Material;

public interface IMaterialService {

	public void SalvarOuAlterar(Material material);

	public Material buscarPorId(Integer id);

	public void excluir(Integer id);

	public List<Material> listar();
}
