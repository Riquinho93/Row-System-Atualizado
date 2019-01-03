package com.myfinishproject.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.myfinishproject.model.Colecao;


public class DeletColecao extends Panel {

	private static final long serialVersionUID = 1L;
	private Colecao user = new Colecao();

	public DeletColecao(String id, final Colecao answer) {
		super(id);
		this.user = answer;

		Form<Colecao> form = new Form<Colecao>("resposta");

		add(new Label("msg", "Confirmar exclusão da coleção?"));

		// Tamanho do Modal
		// modalWindow.setInitialHeight(200);
		// modalWindow.setInitialWidth(350);

		// Confirmando a operação
		AjaxButton yesButton = new AjaxButton("sim") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (target != null) {
					user.setAnswer(true);
					executarAoSalvar(target, user);

				}
			}
		};
		// Resposta de cancelar operação
		AjaxButton noButton = new AjaxButton("nao") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (target != null) {
					user.setAnswer(false);
					executarAoSalvar(target, user);

				}
			}
		};
		add(form);
		yesButton.setOutputMarkupId(true);
		noButton.setOutputMarkupId(true);

		form.add(yesButton);
		form.add(noButton);

	}

	// Enviando os dados para o HomePage
	public void executarAoSalvar(AjaxRequestTarget target, Colecao colecaoModel) {

	}

}