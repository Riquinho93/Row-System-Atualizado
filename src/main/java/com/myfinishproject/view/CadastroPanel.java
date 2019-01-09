package com.myfinishproject.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.myfinishproject.model.Endereco;
import com.myfinishproject.model.Funcao;
import com.myfinishproject.model.Funcionario;

public class CadastroPanel extends Panel {

	private static final long serialVersionUID = 8991195474675368668L;

	private Form<Funcionario> formFunc;
	private Form<Endereco> formEnd;
//	private Funcionario funcionario;
//	private Endereco endereco;

	public CadastroPanel(String id) {
		this(id, new Funcionario(), new Endereco());
	}

	public CadastroPanel(String id, Funcionario funcionario, Endereco endereco) {
		super(id);
		formFunc = new Form<Funcionario>("formFunc", new CompoundPropertyModel<Funcionario>(funcionario));
		formEnd = new Form<Endereco>("formEnd", new CompoundPropertyModel<Endereco>(endereco));

		TextField<String> nome = new TextField<>("nome");
		TextField<String> telefone = new TextField<>("telefone");
		TextField<String> email = new TextField<>("email");

		TextField<String> logradouro = new TextField<>("logradouro");
		NumberTextField<Integer> numero = new NumberTextField<>("numero");
		TextField<String> cidade = new TextField<>("cidade");
		TextField<String> estado = new TextField<>("estado");
		

		nome.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		email.setOutputMarkupId(true);
		logradouro.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		cidade.setOutputMarkupId(true);
		estado.setOutputMarkupId(true);

		ChoiceRenderer<Funcao> renderer = new ChoiceRenderer<Funcao>("descricao");
		IModel<List<Funcao>> model = new LoadableDetachableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Funcao> load() {
				return Funcao.funcoes();
			}
		};

		DropDownChoice<Funcao> funcoes = new DropDownChoice<>("funcao", model, renderer);

		// funcionario.setEndereco(endereco);
		AjaxButton button = new AjaxButton("submit") {

			private static final long serialVersionUID = 994698440577863113L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				executarAoSalvar(target, funcionario, endereco);
				target.add(nome);
				target.add(telefone);
				target.add(email);
				target.add(logradouro);
				target.add(numero);
				target.add(cidade);
				target.add(estado);

			}
		};
		button.setOutputMarkupId(true);
	
//		add(formEnd);
		formFunc.add(nome);
		formFunc.add(telefone);
		formFunc.add(email);

		formEnd.add(logradouro);
		formEnd.add(numero);
		formEnd.add(cidade);
		formEnd.add(estado);
		formFunc.add(funcoes);
		formFunc.add(formEnd);
		formFunc.add(button);
		add(formFunc);
		

	}

	// Enviando os dados para o HomePage
	public void executarAoSalvar(AjaxRequestTarget target, Funcionario funcionario, Endereco endereco) {

	}

}
