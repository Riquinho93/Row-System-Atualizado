package com.myfinishproject.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;

import com.myfinishproject.model.Usuario;

public class UsuarioPanel extends Panel{

	private static final long serialVersionUID = -6534497016691513947L;
	
	public UsuarioPanel(String id) {
		this(id, new Usuario());
	}

	public UsuarioPanel(String id, Usuario usuario) {
		super(id);
		
		
	}
	
	public void executarAoSalvar(AjaxRequestTarget target, Usuario usuario) {}

}
