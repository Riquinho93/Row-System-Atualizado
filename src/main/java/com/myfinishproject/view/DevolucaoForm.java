package com.myfinishproject.view;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.myfinishproject.HomePage;

public class DevolucaoForm extends HomePage{
	
	private static final long serialVersionUID = 1080136208154144002L;

	public DevolucaoForm(PageParameters parameters) {
		add(new Label("modelo", parameters.get("modelo")));
		add(new Label("largura", parameters.get("largura")));
		add(new Label("tipoEnfesto", parameters.get("tipoEnfesto")));
		add(new Label("dataSaida", parameters.get("dataSaida")));
		add(new Label("dataRetorno", parameters.get("dataRetorno")));
		add(new Label("status", parameters.get("status")));
		
		add(new Label("dev", "ORDEM DE SERVIÇOS DEVOLUTIVAS")).setOutputMarkupId(true);
	}

}
