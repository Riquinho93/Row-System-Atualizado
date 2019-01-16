package com.myfinishproject.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.model.Usuario;
import com.myfinishproject.service.UsuarioService;

public class Login extends WebPage {

	private static final long serialVersionUID = -2850628051987758424L;

	private Form<Usuario> formularioLogin;
	private Usuario filtrarUsuario;
	@SpringBean(name = "usuarioService")
	private UsuarioService usuarioService;

	public Login() {
		filtrarUsuario = new Usuario();
		formularioLogin = new Form<Usuario>("formularioLogin", new CompoundPropertyModel<>(filtrarUsuario));
		final TextField<String> login = new TextField<String>("login");
		final PasswordTextField senha = new PasswordTextField("senha");

		login.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);

		formularioLogin.add(login);
		formularioLogin.add(senha);

		/*
		 * final Label errorLogin = new Label("errorLogin",
		 * Model.of("Erro ao realizar o login"));
		 * errorLogin.setOutputMarkupId(true).setVisible(false);
		 */

		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("submit", formularioLogin) {

			private static final long serialVersionUID = -5095534494215850537L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Search search = new Search(Usuario.class);

				search.addFilterEqual("login", login.getModelObject());
				search.addFilterEqual("senha", senha.getModelObject());

				List<Usuario> lista = usuarioService.search(search);

				if (lista != null && !lista.isEmpty()) {

					getSession().setAttribute("userName", lista.get(0));
					setResponsePage(ColecaoForm.class);
				}

				super.onSubmit();
			}

		};
		formularioLogin.setOutputMarkupId(true);
		formularioLogin.add(ajaxSubmitLink).setOutputMarkupId(true);
		add(formularioLogin);
	}
}
