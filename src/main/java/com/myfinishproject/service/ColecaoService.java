package com.myfinishproject.service;

import java.util.ArrayList;
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
		List<String> lista = new ArrayList<>();
		lista = validacao(colecao);
		if (lista == null) {
			colecaoDao.SalvarOuAlterar(colecao);
		}else {
//			return lista;
		}

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

	private List<String> validacao(Colecao colecao) {
		List<String> listaMsg = new ArrayList<String>();
		String mensagem;
		if (colecao.getNome() == null) {
			mensagem = "Campo nome é Obrigatório!";
			listaMsg.add(mensagem);
			
		}
		if (colecao.getDtEntrada() == null) {
			mensagem = "Campo data é Obrigatório!";
			listaMsg.add(mensagem);
		}

		return listaMsg;
	}

}
