package com.myfinishproject.view;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

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
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.HomePage;
import com.myfinishproject.model.Colecao;
import com.myfinishproject.model.Produto;
import com.myfinishproject.service.ProdutoService;

public class ProdutoForm extends HomePage {

	private static final long serialVersionUID = 1050984839123848135L;

	private Form<Produto> form = new Form<Produto>("form");
	private Form<Produto> produtoForm;
	private ModalWindow modalWindowDel;
	private Produto filtrarProduto;
	private ModalWindow modalWindow;
	private List<Produto> produtoLista = new LinkedList<Produto>();
	private PageableListView<Produto> listView;
	private WebMarkupContainer listcontainer;
	private LoadableDetachableModel<List<Produto>> atualizarLista;
	@SpringBean(name = "produtoService")
	private ProdutoService produtoService;

	public ProdutoForm(Colecao colecao) {
		this(new PageParameters(), new Colecao());
	}

	public ProdutoForm(PageParameters parameters, Colecao colecao) {

		add(new Label("nome", parameters.get("nome")));
		add(new Label("dtEntrada", parameters.get("dtEntrada")));
		produtoLista = produtoService.listar(colecao.getColecaoId());

		modalWindow = new ModalWindow("modalWindow");
		// Tamanho do Modal
		modalWindow.setInitialHeight(550);
		modalWindow.setInitialWidth(1000);
		modalWindow.setOutputMarkupId(true);
		add(modalWindow);

		modalWindowDel = new ModalWindow("modalWindowDel");
		// Tamanho do Modal Delete
		modalWindowDel.setInitialHeight(200);
		modalWindowDel.setInitialWidth(200);
		modalWindowDel.setOutputMarkupId(true);
		add(modalWindowDel);

		add(container());
		add(filtrar());

		add(new AjaxLink<String>("criarProduto") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ProdutoPanel produtoPanel = new ProdutoPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Produto produto) {
						produto.setColecao(colecao);
						produtoService.SalvarOuAlterar(produto);
						produtoLista.add(produto);
						target.add(listcontainer);
						modalWindow.close(target);
					};
				};
				produtoPanel.setOutputMarkupId(true);
				add(produtoPanel);
				modalWindow.setContent(produtoPanel);
				modalWindow.show(target);
			}
		});

	}

	private WebMarkupContainer container() {
		listcontainer = new WebMarkupContainer("container");
		listcontainer.setOutputMarkupId(true);
		atualizarLista = new LoadableDetachableModel<List<Produto>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Produto> load() {
				return produtoLista;
			}
		};

		listView = new PageableListView<Produto>("listView", atualizarLista, 5) {

			@Override
			protected void populateItem(ListItem<Produto> item) {
				Produto user = item.getModelObject();
				item.add(new Label("modelo", user.getModelo()));
				item.add(new Label("colecao", user.getColecao().getColecaoId()));
				item.add(new Label("dataSaida", user.getDataSaida()));
				item.add(new Label("dataRetorno", user.getDataRetorno()));
				item.add(new Label("largura", user.getLargura()));
				item.add(new Label("tipoEnfesto", user.getTipoEnfesto()));
				item.add(new Label("status", user.getStatus()));
				item.add(remover(user.getId()));
				item.add(editando(user));
			}
		};
		add(listView);
		listView.setOutputMarkupId(true);
		listcontainer.add(listView);
		add(new PagingNavigator("pag", listView));

		return listcontainer;
	}

	// Editando os campos
	AjaxLink<Produto> editando(final Produto user) {
		AjaxLink<Produto> button1 = new AjaxLink<Produto>("alt") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ProdutoPanel produtoPanel = new ProdutoPanel(modalWindow.getContentId(), user) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Produto produto) {
//						produtoService.SalvarOuAlterar(produto);
						target.add(listcontainer);
						modalWindow.close(target);
					};
				};

				produtoPanel.setOutputMarkupId(true);
				modalWindow.setContent(produtoPanel);
				modalWindow.show(target);

			}
		};

		button1.setOutputMarkupId(true);
		form.add(button1);
		return button1;
	}

	// Filtrar campos
	public Form<Produto> filtrar() {
		filtrarProduto = new Produto();
		produtoForm = new Form<Produto>("filtrarForm", new CompoundPropertyModel<Produto>(filtrarProduto));

		TextField<String> modelo = new TextField<>("modelo");
		modelo.setOutputMarkupId(true);
		produtoForm.add(modelo);

		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", produtoForm) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				Search search = new Search();

				if (filtrarProduto.getModelo() != null && !filtrarProduto.getModelo().equals("")) {
					search.addFilterLike("modelo", "%" + filtrarProduto.getModelo() + "%");
				}
				produtoLista = produtoService.search(search);
				target.add(listcontainer);
			}
		};
		produtoForm.setOutputMarkupId(true);
		produtoForm.add(ajaxSubmitLink).setOutputMarkupId(true);
		return produtoForm;
	}

	protected AjaxLink<Produto> remover(Integer id) {
		AjaxLink<Produto> ajaxLink = new AjaxLink<Produto>("excluir") {

			private static final long serialVersionUID = 1L;
			Produto answer = new Produto();

			@Override
			public void onClick(AjaxRequestTarget target) {

				DeletProduto deletProduto = new DeletProduto(modalWindowDel.getContentId(), answer) {

					private static final long serialVersionUID = 1L;

					public void executarAoExcluir(AjaxRequestTarget target, Produto produto) {
						if (produto.isAnswer() == true) {
							produtoService.excluir(id);
							target.add(listcontainer);
						}
					};
				};
				deletProduto.setOutputMarkupId(true);
				modalWindowDel.setContent(deletProduto);
				modalWindowDel.show(target);

			}
		};
		ajaxLink.setOutputMarkupId(true);
		form.add(ajaxLink);
		return ajaxLink;
	}

}
