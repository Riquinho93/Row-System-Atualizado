package com.myfinishproject;

import com.myfinishproject.view.ColecaoForm;
import com.myfinishproject.view.Login;

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
		
		add(new Link<Void>("sair"){
			
			private static final long serialVersionUID = 1L;

			public void onClick() {
				getSession().invalidate();
				setResponsePage(ColecaoForm.class);
			}
		});
add(new Link<Void>("logar"){
			
			private static final long serialVersionUID = 1L;

			public void onClick() {
				getSession().invalidate();
				setResponsePage(ColecaoForm.class);
			}
		});

		add(rederizandoPagina());

		add(principalPagina());

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

}
