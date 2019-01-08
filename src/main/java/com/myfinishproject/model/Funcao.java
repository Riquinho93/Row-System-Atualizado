package com.myfinishproject.model;

import java.util.ArrayList;
import java.util.List;

public enum Funcao {

	FACCIONISTA("Faccionista"), CORTADOR("Cortador"), FUNCIONARIO("Funcionario");
	private String descricao;

	private Funcao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Funcao> funcoes() {
		List<Funcao> funcao = new ArrayList<>();
		funcao.add(CORTADOR);
		funcao.add(FACCIONISTA);
		funcao.add(FUNCIONARIO);
		return funcao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
