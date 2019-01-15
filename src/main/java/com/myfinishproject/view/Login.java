package com.myfinishproject.view;



import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.myfinishproject.model.Usuario;

public class Login extends WebPage{
	
	private static final long serialVersionUID = -2850628051987758424L;
	
	private Form<Usuario> formularioLogin;

	public Login() {
		
		final TextField<String> campoNomeUsuario = new TextField<String>("nomeUsuario", new Model<String>());
		final PasswordTextField campoPassword = new PasswordTextField("password", new Model<String>());
		
		final Label errorLogin = new Label("errorLogin", Model.of("Erro ao realizar o login"));
		errorLogin.setOutputMarkupId(true).setVisible(false);
		
		 formularioLogin = new Form<Usuario>("formularioLogin") {

			private static final long serialVersionUID = -5095534494215850537L;
			
			@Override
			protected void onSubmit() {
				Usuario usuario = new Usuario();
				String nomeUsuario = campoNomeUsuario.getModelObject();
				String password = campoPassword.getModelObject();
				if(nomeUsuario.equals("123") && password.equals("123")) {
					getSession().setAttribute("userName", nomeUsuario);
					setResponsePage(ColecaoForm.class);
				}else {
					errorLogin.setVisible(true);
				}
				super.onSubmit();
			}
			
		};
		add(errorLogin, formularioLogin);
		formularioLogin.add(campoNomeUsuario, campoPassword);
	}
}
