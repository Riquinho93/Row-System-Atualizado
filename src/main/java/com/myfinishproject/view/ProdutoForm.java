package com.myfinishproject.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.myfinishproject.HomePage;

public class ProdutoForm extends HomePage{
	
	private static final long serialVersionUID = 1050984839123848135L;
	
	private ModalWindow modalWindow;

	public ProdutoForm(PageParameters parameters) {
		
		add(new Label("nome", parameters.get("nome")));
		add(new Label("dtEntrada", parameters.get("dtEntrada")));
		
		modalWindow = new ModalWindow("modalWindow");
		add(modalWindow);
		
		add(new AjaxLink<String>("criarProduto") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				ProdutoPanel produtoPanel = new ProdutoPanel(modalWindow.getContentId()) {
					
					
				};
				produtoPanel.setOutputMarkupId(true);
				add(produtoPanel);
				modalWindow.setContent(produtoPanel);
				modalWindow.show(target);
			}
		});
		
	}

}
