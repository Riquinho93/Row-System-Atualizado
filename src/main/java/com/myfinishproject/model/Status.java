package com.myfinishproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum Status implements Serializable{

	ANDAMENTO("Andamento"), ATRASADO("Atrasado"), ADIATADO("Adiatado"), FINALIZADO("Finalizado");
	private String descricaoStatus;

	private Status(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
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

	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	public String converterParaString(Status status2) {
		String status = "";
			if (status2.equals(ADIATADO)) {
				status = "ADIATADO";
				return status;
			}else if (status2.equals(ANDAMENTO)) {
			status = "ANDAMENTO";
			return status;
		}
			System.out.println("Status: " + status2);

		return status;
	}


}
