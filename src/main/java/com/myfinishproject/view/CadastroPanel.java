package com.myfinishproject.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.myfinishproject.model.Endereco;
import com.myfinishproject.model.Funcionario;

public class CadastroPanel extends Panel{

	private static final long serialVersionUID = 8991195474675368668L;
	
	private Form<Funcionario> formFunc;
	private Form<Endereco> formEnd;
	private Funcionario funcionario;
	private Endereco endereco;
	private List<String> funcoes;
	
	public CadastroPanel(String id) {
		this(id, new Funcionario());
	}

	public CadastroPanel(String id, Funcionario funcionario) {
		super(id);
		endereco = new Endereco();
		funcoes = new ArrayList<String>();
		funcoes.add("Cortador");
		funcoes.add("Faccionista");
		funcoes.add("Funcionario");
		
		formFunc = new Form<Funcionario>("formFunc", new CompoundPropertyModel<Funcionario>(funcionario));
		formEnd = new Form<Endereco>("formEnd", new CompoundPropertyModel<Endereco>(endereco));
		
		add(formFunc);
		add(formEnd);
		cadastrar();

	}
	
	private void cadastrar() {
		TextField<String> nome = new TextField<>("nome");
//		TextField<String> funcao = new TextField<>("funcao");
		TextField<String> telefone = new TextField<>("telefone");
		TextField<String> email = new TextField<>("email");
//		TextField<Endereco> endereco =  new TextField<>("endereco");

		TextField<String> logradouro = new TextField<>("logradouro");
		NumberTextField<Integer> numero = new NumberTextField<>("numero");
		TextField<String> cidade = new TextField<>("cidade");
		TextField<String> estado = new TextField<>("estado");
		

		/*formFunc.add(new DropDownChoice("funcao", new Model(), funcoes) {
			protected boolean localizeDisplayValues() {
				return true;
			}
		});*/

		estado.setOutputMarkupId(true);
		nome.setOutputMarkupId(true);
//		funcao.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		email.setOutputMarkupId(true);
		logradouro.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		cidade.setOutputMarkupId(true);

		// funcionario.setEndereco(endereco);
		add(new AjaxLink<String>("submit") {

			private static final long serialVersionUID = 994698440577863113L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				
				executarAoSalvar(target, funcionario);
				target.add(nome);
				target.add(telefone);
				target.add(email);
				target.add(logradouro);
				target.add(numero);
				target.add(cidade);
				target.add(estado);
			}

		});
		formFunc.add(nome);
//		formFunc.add(funcao);
		formFunc.add(telefone);
		formFunc.add(email);
//		formFunc.add(endereco);

		formEnd.add(logradouro);
		formEnd.add(numero);
		formEnd.add(cidade);
		formEnd.add(estado);

	}
	
	// Enviando os dados para o HomePage
		public void executarAoSalvar(AjaxRequestTarget target, Funcionario funcionario) {

		}

}
