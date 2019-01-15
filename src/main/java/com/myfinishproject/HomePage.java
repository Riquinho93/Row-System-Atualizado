package com.myfinishproject;

import com.myfinishproject.view.FuncionarioForm;
import com.myfinishproject.view.ColecaoForm;
import com.myfinishproject.view.DevolucaoForm;
import com.myfinishproject.view.Login;
import com.myfinishproject.view.UsuarioForm;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		
		String userName = (String) getSession().getAttribute("userName");
		if (userName == null) {
			 setResponsePage(Login.class);
			 return;
		}
//		UsuarioForm usuarioForm = new UsuarioForm();
//		usuarioForm.setEnabled(false);
		
		add(new Link<Void>("sair"){
			
			private static final long serialVersionUID = 1L;

			public void onClick() {
				getSession().invalidate();
				setResponsePage(ColecaoForm.class);
			}
		});

		add(rederizandoPagina());

		add(principalPagina());
		
		add(cadastro());
		add(usuarios());
		add(devolucao());

	}

	// Metodo deRederizar a pagina
	public AjaxLink<?> rederizandoPagina() {
		// Botão normal
		AjaxLink<Object> button = new AjaxLink<Object>("principal") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget arg0) {
				setResponsePage(ColecaoForm.class);

			}
		};
		button.setOutputMarkupId(true);
		add(button);
		return button;
	}

	// Metodo deRederizar a pagina
	public AjaxLink<?> principalPagina() {
		// Botão normal
		AjaxLink<Object> button = new AjaxLink<Object>("menu") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget arg0) {
				setResponsePage(ColecaoForm.class);

			}
		};
		button.setOutputMarkupId(true);
		add(button);
		return button;
	}
	
	// Chamada do cadastro
		public AjaxLink<?> cadastro() {
			// Botão normal
			AjaxLink<Object> button = new AjaxLink<Object>("pagCadastro") {

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget arg0) {
					setResponsePage(FuncionarioForm.class);

				}
			};
			button.setOutputMarkupId(true);
			add(button);
			return button;
		}
		
		// Chamada do usuario
				public AjaxLink<?> usuarios() {
					// Botão normal
					AjaxLink<Object> button = new AjaxLink<Object>("pagUsuario") {

						private static final long serialVersionUID = 1L;

						@Override
						public void onClick(AjaxRequestTarget arg0) {
							setResponsePage(UsuarioForm.class);

						}
					};
					button.setOutputMarkupId(true);
					add(button);
					return button;
				}
				
				// Chamada de Devolucao
				public AjaxLink<?> devolucao() {
					// Botão normal
					AjaxLink<Object> button = new AjaxLink<Object>("pagDevolucao") {

						private static final long serialVersionUID = 1L;

						@Override
						public void onClick(AjaxRequestTarget arg0) {
							setResponsePage(DevolucaoForm.class);

						}
					};
					button.setOutputMarkupId(true);
					add(button);
					return button;
				}
		

}
