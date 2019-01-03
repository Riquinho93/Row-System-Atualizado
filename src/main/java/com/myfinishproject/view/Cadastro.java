package com.myfinishproject.view;

import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.myfinishproject.HomePage;
import com.myfinishproject.model.Endereco;
import com.myfinishproject.model.Funcionario;

public class Cadastro extends HomePage{
	
	private static final long serialVersionUID = 2474313326427632580L;
	
	private Form<Funcionario> formFunc;
	private Form<Endereco> formEnd;
	private Funcionario funcionario;
	private Endereco endereco;
	
	public Cadastro() {
		endereco = new Endereco();
		funcionario = new Funcionario();
		formFunc = new Form<>("formFunc",new  CompoundPropertyModel<Funcionario>(funcionario));
		formEnd = new Form<>("formEnd", new CompoundPropertyModel<Endereco>(endereco));
		add(formFunc);
		add(formEnd);
		cadastrar();
	}
	
	private void cadastrar() {
		TextField<String> nome = new TextField<>("nome");
		TextField<String> funcao = new TextField<>("funcao");
		TextField<String> telefone = new TextField<>("telefone");
		TextField<String> email = new TextField<>("email");
//		TextField<Endereco> endereco =  new TextField<>("endereco");
		
		TextField<String> logradouro = new TextField<>("logradouro");
		NumberTextField<Integer> numero = new NumberTextField<>("numero");
		TextField<String> cidade = new TextField<>("cidade");
		nome.setOutputMarkupId(true);
		funcao.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		email.setOutputMarkupId(true);
		logradouro.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		cidade.setOutputMarkupId(true);
		
		//funcionario.setEndereco(endereco);
		AjaxButton ajaxButton = new AjaxButton("submit") {
			
			@Override
			public void onSubmit() {
				
				super.onSubmit();
			}
		};
		formFunc.add(nome);
		formFunc.add(funcao);
		formFunc.add(telefone);
		formFunc.add(email);
//		formFunc.add(endereco);
		
		formEnd.add(logradouro);
		formEnd.add(numero);
		formEnd.add(cidade);
		formEnd.add(ajaxButton);
	}
}
