package com.myfinishproject.model;

import java.util.ArrayList;
import java.util.List;

public enum TipoEnfesto {

	UNICO("Unico"), DUPLO("Duplo");
	private String descricao;

	private TipoEnfesto(String descricao) {
		this.descricao = descricao;
	}

	public static List<TipoEnfesto> tipo() {
		List<TipoEnfesto> tipos = new ArrayList<>();
		tipos.add(UNICO);
		tipos.add(DUPLO);
		return tipos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String ConverterParaString(TipoEnfesto enfesto) {
		String tipoEnfesto = "";
		
		if(enfesto.equals(UNICO)) {
			tipoEnfesto = "UNICO";
			return tipoEnfesto;
		}
		if(enfesto.equals(DUPLO)) {
			tipoEnfesto = "DUPLO";
			return tipoEnfesto;
		}
		return tipoEnfesto;
	}

}
