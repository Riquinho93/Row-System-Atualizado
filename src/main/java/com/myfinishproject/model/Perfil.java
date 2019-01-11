package com.myfinishproject.model;

import java.util.ArrayList;
import java.util.List;

public enum Perfil {

	USUARIO("Usuario"), ADMIN("Admin");
	private String usuario;

	private Perfil(String usuario) {
		this.usuario = usuario;
	}

	public static List<Perfil> perfil() {
		List<Perfil> perfils = new ArrayList<>();
		perfils.add(USUARIO);
		perfils.add(ADMIN);
		return perfils;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
