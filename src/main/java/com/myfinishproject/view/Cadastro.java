package com.myfinishproject.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.HomePage;
import com.myfinishproject.model.Endereco;
import com.myfinishproject.model.Funcionario;
import com.myfinishproject.service.EnderecoService;
import com.myfinishproject.service.FuncionarioService;

public class Cadastro extends HomePage {

	private static final long serialVersionUID = 2474313326427632580L;

	private Form<Funcionario> formFunc = new Form<>("formFunc");
//	private Form<Endereco> formEnd;
	private Form<Funcionario> form2;
	private Funcionario funcionario;
	private Endereco endereco;
	private List<Funcionario> funcionariosList = new ArrayList<>();
	private PageableListView<Funcionario> listView;
	private LoadableDetachableModel<List<Funcionario>> atualizarLista;
	private WebMarkupContainer listContainer = null;
	private ModalWindow modalWindow;
	private ModalWindow modalWindowDel;
	@SpringBean(name = "funcionarioService")
	private FuncionarioService funcionarioService;
	@SpringBean(name= "enderecoService")
	private EnderecoService enderecoService;
	private Funcionario filtrar;

	public Cadastro() {

		endereco = new Endereco();
		funcionario = new Funcionario();
		// formEnd = new Form<>("formEnd", new
		// CompoundPropertyModel<Endereco>(endereco));

		funcionariosList = funcionarioService.listar();
		
		add(filtrar());

//		add(formEnd);

		// Chamando a listView
		add(container());

		modalWindow = new ModalWindow("modalWindow");
		// new CadastroPanel(modalWindow.getId());
		// Tamanho do Modal
		modalWindow.setInitialHeight(400);
		modalWindow.setInitialWidth(800);
		modalWindow.setOutputMarkupId(true);
		add(modalWindow);

		// Modal Window do delete
		modalWindowDel = new ModalWindow("modalWindowDel");
		// Tamanho
		modalWindowDel.setInitialHeight(200);
		modalWindowDel.setInitialWidth(200);
		modalWindowDel.setOutputMarkupId(true);
		add(modalWindowDel);
		// Criando o botao para o Modal
		add(new AjaxLink<String>("viewLink") {

			private static final long serialVersionUID = -7766269695313736383L;

			@Override
			public void onClick(AjaxRequestTarget target) {

				CadastroPanel cadastroPanel = new CadastroPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 277997013286385910L;

					public void executarAoSalvar(AjaxRequestTarget target, Funcionario funcionario, Endereco endereco) {
						
						funcionario.setEndereco(endereco);
						enderecoService.SalvarOuAlterar(endereco);
						funcionarioService.SalvarOuAlterar(funcionario);
						funcionariosList.add(funcionario);
						target.add(listContainer);
						modalWindow.close(target);
					};

				};
				cadastroPanel.setOutputMarkupId(true);
				add(cadastroPanel);
				modalWindow.setContent(cadastroPanel);
				modalWindow.show(target);
			};

		});


	}

	private WebMarkupContainer container() {

		listContainer = new WebMarkupContainer("theContainer");
		listContainer.setOutputMarkupId(true);

		atualizarLista = new LoadableDetachableModel<List<Funcionario>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Funcionario> load() {
				return funcionariosList;
			}
		};

		listView = new PageableListView<Funcionario>("listView", atualizarLista, 5) {

			private static final long serialVersionUID = -8503564664744203394L;

			@Override
			protected void populateItem(ListItem<Funcionario> item) {
				Funcionario user = item.getModelObject();
				item.add(new Label("id", user.getId()));
				item.add(new Label("nome", user.getNome()));
				item.add(new Label("funcao", user.getFuncao()));
				item.add(new Label("telefone", user.getTelefone()));
				item.add(new Label("email", user.getEmail()));
				item.add(new Label("endereco", user.getEndereco().getId()));
				item.add(editando(user, user.getEndereco()));
				item.add(remover(user.getId()));
			}
		};
		add(listView);
		listView.setOutputMarkupId(true);
		listContainer.add(listView);

		add(new PagingNavigator("pag", listView));
		return listContainer;

	}

	public Form<Funcionario> filtrar() {
		filtrar = new Funcionario();
		form2 = new Form<Funcionario>("form2", new CompoundPropertyModel<Funcionario>(filtrar));
		TextField<String> nome = new TextField<String>("nome");
		nome.setOutputMarkupId(true);
		form2.add(nome);
		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", form2) {

			private static final long serialVersionUID = 8104552052869900594L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search(Funcionario.class);

				if (filtrar.getNome() != null && !filtrar.getNome().equals("")) {
					search.addFilterLike("nome", "%" + filtrar.getNome() + "%");
				}

				funcionariosList = funcionarioService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}

		};
		form2.setOutputMarkupId(true);
		form2.add(ajaxSubmitLink).setOutputMarkupId(true);
		return form2;

	}

	// Editando
	AjaxLink<Funcionario> editando(final Funcionario funcionario, Endereco endereco) {
		AjaxLink<Funcionario> editar = new AjaxLink<Funcionario>("alterar") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				CadastroPanel cadastroPanel = new CadastroPanel(modalWindow.getContentId(), funcionario, endereco) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Funcionario funcionario, Endereco endereco) {
						funcionario.setEndereco(endereco);
						enderecoService.SalvarOuAlterar(endereco);
						target.add(listContainer);
						funcionarioService.SalvarOuAlterar(funcionario);
						modalWindow.close(target);
					};
				};
				cadastroPanel.setOutputMarkupId(true);
				modalWindow.setContent(cadastroPanel);
				modalWindow.show(target);
			}
		};
		editar.setOutputMarkupId(true);
		formFunc.add(editar);
		return editar;
	}

	// Removendo
	private Component remover(final Integer index) {

		AjaxLink<Funcionario> button = new AjaxLink<Funcionario>("excluir") {
			Funcionario answer = new Funcionario();
			
			private static final long serialVersionUID = 1L;


			@Override
			public void onClick(AjaxRequestTarget target) {
				DeletCadastro deletCadastro = new DeletCadastro(modalWindowDel.getContentId(), answer) {

					private static final long serialVersionUID = 1L;

					public void executarAoExcluir(final AjaxRequestTarget target, Funcionario funcionario) {
						if (funcionario.isAnswer() == true) {
							funcionariosList.remove(index);
							funcionarioService.excluir(index);
							target.add(listContainer);
						}
						modalWindowDel.close(target);
					};
				};
				deletCadastro.setOutputMarkupId(true);
				modalWindowDel.setContent(deletCadastro);
				modalWindowDel.show(target);
			}
		};
		button.setOutputMarkupId(true);
		formFunc.add(button);
		return button;
	}

}
