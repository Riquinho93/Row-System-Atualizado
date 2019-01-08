package com.myfinishproject.model;

import java.util.ArrayList;
import java.util.List;

public enum Status {
	ANDAMENTO("Andamento"), ATRASADO("Atrasado"), ADIATADO("Adiatado"), FINALIZADO("Finalizado");
	private String descricaoStatus;

	private Status(String descricao) {
		this.descricaoStatus = descricao;
	}

	public static List<Status> status() {
		List<Status> lista = new ArrayList<>();
		lista.add(ANDAMENTO);
		lista.add(ADIATADO);
		lista.add(ATRASADO);
		lista.add(FINALIZADO);
		return lista;
	}

	public String getDescricao() {
		return descricaoStatus;
	}

	public void setDescricao(String descricao) {
		this.descricaoStatus = descricao;
	}

}
