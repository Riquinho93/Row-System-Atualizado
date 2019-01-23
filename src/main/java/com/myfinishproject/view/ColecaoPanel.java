package com.myfinishproject.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.myfinishproject.model.Colecao;
import com.myfinishproject.service.AlertFeedback;

public class ColecaoPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ColecaoPanel(String id) {
		this(id, new Colecao());
	}

	public ColecaoPanel(String id, final Colecao colecaoModel) {
		super(id);

		add(new Label("message", "COLEÇÃO"));
		
		final AlertFeedback alertFeedback = new AlertFeedback("feedbackMessage");
		
		

		Form<Colecao> form = new Form<Colecao>("form", new CompoundPropertyModel<Colecao>(colecaoModel));

		final TextField<String> nome = new TextField<String>("nome");

		nome.setRequired(true);
		nome.setOutputMarkupId(true);
		
		  final Label errorLogin = new Label("errorLogin",
		  Model.of("Login Incorreto!!"));
		  errorLogin.setOutputMarkupId(true).setVisible(false);
		 add(errorLogin);

		// Criando botão de enviar
		// Botão Ajax
		AjaxButton ajaxButton = new AjaxButton("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				
				alertFeedback.success("Coleção cadastrada com sucesso!!!");
				executarAoSalvar(target, colecaoModel);
				target.add(nome);
			}
		};
		ajaxButton.setOutputMarkupId(true);
		add(alertFeedback);
		add(form);
		form.add(nome);
		form.add(ajaxButton);
		// Data
		DatePicker datePickerInicial = new DatePicker() {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean alignWithIcon() {
				return true;
			}

			@Override
			protected boolean enableMonthYearSelection() {
				return false;
			}
		};

		DateTextField data = new DateTextField("data", "dd/MM/yyyy");
		datePickerInicial.setAutoHide(true);
		data.add(datePickerInicial);
		data.setOutputMarkupId(true);
		form.add(data);

	}

	// Enviando os dados para o HomePage
	public void executarAoSalvar(AjaxRequestTarget target, Colecao colecaoModel) {

	}

}
