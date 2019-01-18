package com.myfinishproject.model;

import java.util.ArrayList;
import java.util.List;

public enum Status {
	
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
	
	public String converterParaString(int num) {
		String status = "";
		List<Status> listaStatus = new ArrayList<>();
		for(Status lista:  listaStatus) {
			if (lista.equals(0) && num == 0) {
				status = "Andamento";
				return status;
			}
			if (lista.equals(1) && num == 1) {
				status = "Adiatado";
				return status;
			}
			if (lista.equals(2) && num == 2) 
				status = "Atrasado";
			 if (lista.equals(3) && num == 3) 
				status = "Finalizado";
			
		}
		System.out.println("Status: " + status);
		return status;
	}
	

}
