package com.myfinishproject.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.myfinishproject.HomePage;
import com.myfinishproject.model.Produto;

public class DevolucaoForm extends HomePage{
	
	private static final long serialVersionUID = 1080136208154144002L;
	
	private List<Produto> listaDevolutivas = new ArrayList<>();
	
	public DevolucaoForm() {
	}
	
	public DevolucaoForm(Produto produto) {
		listaDevolutivas.add(produto);
	/*	add(new Label("dev", "ORDEM DE SERVIÇOS DEVOLUTIVAS"));
		add(new Label("modelo", produto.getModelo()));
		add(new Label("largura", produto.getLargura()));
		add(new Label("tipoEnfesto", produto.getTipoEnfesto()));
		add(new Label("dataSaida", produto.getDataSaida()));
		add(new Label("dataRetorno", produto.getDataRetorno()));
		add(new Label("status", produto.getStatus()));*/
		
	}

}
