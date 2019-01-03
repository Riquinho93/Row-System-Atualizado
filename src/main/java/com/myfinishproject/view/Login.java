package com.myfinishproject.view;



import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class Login extends WebPage{
	
	private static final long serialVersionUID = -2850628051987758424L;

	public Login() {
		
		final TextField<String> campoNomeUsuario = new TextField<String>("nomeUsuario", new Model<String>());
		final PasswordTextField campoPassword = new PasswordTextField("password", new Model<String>());
		
		final Label errorLogin = new Label("errorLogin", Model.of("Erro ao realizar o login"));
		errorLogin.setOutputMarkupId(true).setVisible(false);
		
		Form<String> formularioLogin = new Form<String>("formularioLogin") {

			private static final long serialVersionUID = -5095534494215850537L;
			
			@Override
			protected void onSubmit() {
				String nomeUsuario = campoNomeUsuario.getModelObject();
				String password = campoPassword.getModelObject();
				if(nomeUsuario.equals("henrique") && password.equals("admin")) {
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
