package com.myfinishproject.view;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.LoadableDetachableModel;

import com.myfinishproject.HomePage;
import com.myfinishproject.model.Endereco;
import com.myfinishproject.model.Funcionario;
import com.myfinishproject.model.Usuario;

public class UsuarioForm extends HomePage {

	private Form<Usuario> formUsuario = new Form<Usuario>("formUsuario");
	private List<Usuario> listausuarios = new LinkedList<>();
	private ModalWindow modalWindow;
	private WebMarkupContainer listContainer = null;
	private LoadableDetachableModel<List<Usuario>> atualiazarUsuarios;

	public UsuarioForm() {

		modalWindow = new ModalWindow("modalWindow");
		// Tamanho do Modal
		modalWindow.setInitialHeight(350);
		modalWindow.setInitialWidth(530);
		modalWindow.setOutputMarkupId(true);
		add(modalWindow);

		// Criando o botao para o Modal
		add(new AjaxLink<String>("viewLink") {

			private static final long serialVersionUID = -7766269695313736383L;

			@Override
			public void onClick(AjaxRequestTarget target) {

				UsuarioPanel usuarioPanel = new UsuarioPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 277997013286385910L;

					public void executarAoSalvar(AjaxRequestTarget target, Usuario usuario) {

						target.add(listContainer);
						modalWindow.close(target);
					};

				};
				usuarioPanel.setOutputMarkupId(true);
				add(usuarioPanel);
				modalWindow.setContent(usuarioPanel);
				modalWindow.show(target);
			};

		});

	}

}
