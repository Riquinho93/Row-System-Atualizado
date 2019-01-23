
package com.myfinishproject.view;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.myfinishproject.HomePage;
import com.myfinishproject.model.Colecao;
import com.myfinishproject.service.AlertFeedback;
import com.myfinishproject.service.ColecaoService;

public class ColecaoForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Colecao> form = new Form<Colecao>("form");
	private Form<Colecao> form2;
	private ModalWindow modalWindow;
	private ModalWindow modalWindowDel;
	private List<Colecao> colecaoModels = new LinkedList<Colecao>();

	private PageableListView<Colecao> listView = null;
	// Criando um container
	private WebMarkupContainer listContainer = null;
	private LoadableDetachableModel<List<Colecao>> loadList;
	private Colecao colecaoForm;

	@SpringBean(name = "colecaoService")
	private ColecaoService colecaoService;

	public ColecaoForm() {
		
		colecaoModels = colecaoService.listar();
		
		/*
		 * Session session = HibernateUtil.getFactory().openSession();
		 * 
		 * session.beginTransaction(); ColecaoModel colecao = new ColecaoModel();
		 * colecao.setNome("Maria"); colecao.setDtEntrada("2018/11/02");
		 * 
		 * colecaoService.SalvarOuAlterar(colecao);
		 * 
		 * session.save(colecao); session.getTransaction().commit(); session.close();
		 */

		/*
		 * Colecao colecao = new Colecao(); colecao.setNome("Maria");
		 * colecao.setDtEntrada("05/03/2018"); colecaoService.SalvarOuAlterar(colecao);
		 */
		// Metodo do container
		add(divConteiner());
		add(filtrar());

		// Chamando pagina OS

		add(new Label("message", "COLEÇÕES"));

		// Modal Windows
		modalWindowDel = new ModalWindow("modalWindowDel");
		// Tamanho do Modal
		modalWindowDel.setInitialHeight(150);
		modalWindowDel.setInitialWidth(250);
		modalWindowDel.setOutputMarkupId(true);
		add(modalWindowDel);

		modalWindow = new ModalWindow("modalWindow");
		new ColecaoPanel(modalWindow.getContentId());

		add(modalWindow);

		// Criando janela da cole
		add(new AjaxLink<String>("viewLink") {
			private static final long serialVersionUID = -182677973237618503L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ColecaoPanel colecaoPanel = new ColecaoPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Colecao colecaoModel) {
						colecaoService.SalvarOuAlterar(colecaoModel);
						colecaoModels.add(colecaoModel);
//						form.clearInput();
//						form.modelChanged();
						// form.setModelObject(userModel);

						target.add(listContainer);
						// modalWindow.clearOriginalDestination();
						
						modalWindow.close(target);
						
					};
				};
				colecaoPanel.setOutputMarkupId(true);
				add(colecaoPanel);
				modalWindow.setContent(colecaoPanel);
				// target.add(listContainer);
				modalWindow.show(target);

			}

		});

		// Colocando nome da janela
		// modalWindow.setTitle("Second Window");

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

		DateTextField data = new DateTextField("dtEntrada", "dd/MM/yyyy");
		datePickerInicial.setAutoHide(true);
		data.add(datePickerInicial);
		data.setOutputMarkupId(true);
		form.add(data);
	}

	// ListView
	private WebMarkupContainer divConteiner() {
		listContainer = new WebMarkupContainer("theContainer");
		listContainer.setOutputMarkupId(true);
		loadList = new LoadableDetachableModel<List<Colecao>>() {

			private static final long serialVersionUID = 1L;

			protected List<Colecao> load() {
				return colecaoModels;
			}
		};
		// Criando a lista View
		listView = new PageableListView<Colecao>("listview", loadList, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Colecao> item) {

				Colecao user = item.getModelObject();

				// item.add(new Label("ID", user.getId()));
				item.add(new Label("nome", user.getNome()));
				item.add(new Label("data", user.getData()));
				item.add(visualizar(item.getIndex(), user));
				item.add(editando(user));
				item.add(removendo(user.getColecaoId()));
			}

		};
		add(listView);
		listView.setOutputMarkupId(true);
		// Encapsular a ListView aqui WebMarkupContainer

		// listContainer.add(new
		// AjaxSelfUpdatingTimerBehavior(Duration.seconds(3)));
		// Aparecer no container
//		listContainer.setOutputMarkupId(true);
		listContainer.add(listView);
		add(new PagingNavigator("pag", listView));

		return listContainer;
	}

	// Indo para a pagina dos produtos
	private AjaxLink<ProdutoForm> visualizar(final int index, final Colecao colecao) {
		AjaxLink<ProdutoForm> button = new AjaxLink<ProdutoForm>("vis") {

			private static final long serialVersionUID = -2066063362744885278L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				PageParameters parametro = new PageParameters();
				parametro.add("colecaoId", colecao.getColecaoId());
				parametro.add("nome", colecao.getNome());
				parametro.add("data", colecao.getData());
//				setResponsePage(ProdutoForm.class, parametro);
				setResponsePage(new ProdutoForm(parametro, colecao));
			}

		};
		button.setOutputMarkupId(true);
		form.add(button);
		return button;
	}

	// Removendo modelo com ajaxLink
	protected Component removendo(final Integer index) {

		AjaxLink<Colecao> button1 = new AjaxLink<Colecao>("del") {
			Colecao answer = new Colecao();

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DeletColecao deletColecao = new DeletColecao(modalWindowDel.getContentId(), answer) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Colecao colecaoModel) {
						if (colecaoModel.isAnswer() == true) {
							// colecaoModels.remove(index);
							colecaoService.excluir(index);
						}
						target.add(listContainer);

						modalWindowDel.close(target);
					};
				};
				deletColecao.setOutputMarkupId(true);
				modalWindowDel.setContent(deletColecao);
				modalWindowDel.show(target);
			}
		};
		button1.setOutputMarkupId(true);
		form.add(button1);
		return button1;
	}

	// Editando os campos
	AjaxLink<Colecao> editando(final Colecao colecaoModel) {
		AjaxLink<Colecao> button1 = new AjaxLink<Colecao>("alt") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ColecaoPanel colecaoPanel = new ColecaoPanel(modalWindow.getContentId(), colecaoModel) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Colecao colecaoModel) {

						target.add(listContainer);
						colecaoService.SalvarOuAlterar(colecaoModel);
						modalWindow.close(target);
					};
				};

				colecaoPanel.setOutputMarkupId(true);
				modalWindow.setContent(colecaoPanel);
				modalWindow.show(target);

			}
		};

		button1.setOutputMarkupId(true);
		form.add(button1);
		return button1;
	}

	public Form<Colecao> filtrar() {
		colecaoForm = new Colecao();
		form2 = new Form<Colecao>("form2", new CompoundPropertyModel<Colecao>(colecaoForm));
		TextField<String> nome = new TextField<String>("nome");
		TextField<String> data = new TextField<String>("data");
		nome.setOutputMarkupId(true);
		data.setOutputMarkupId(true);
		form2.add(nome);
		form2.add(data);
		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", form2) {

			private static final long serialVersionUID = 8104552052869900594L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search(Colecao.class);

				if (colecaoForm.getNome() != null && !colecaoForm.getNome().equals("")) {
					search.addFilterLike("nome", "%" + colecaoForm.getNome() + "%");
				}
				if (colecaoForm.getData() != null && !colecaoForm.getData().equals("")) {
					search.addFilterILike("dtEntrada", "%" + colecaoForm.getData() + "%");
				}
				colecaoModels = colecaoService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}

		};
		form2.setOutputMarkupId(true);
		form2.add(ajaxSubmitLink).setOutputMarkupId(true);
		return form2;

	}

	// Enviando para Pagina OrdemServicoForm
	/*
	 * AjaxLink<ProdutoForm> visualizar(final int index, final Colecao user) {
	 * AjaxLink<ProdutoForm> button1 = new AjaxLink<ProdutoForm>("vis") {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public void onClick(AjaxRequestTarget target) { PageParameters
	 * parameters = new PageParameters(); parameters.add("nome", user.getNome());
	 * parameters.add("dtEntrada", user.getDtEntrada());
	 * setResponsePage(ProdutoForm.class, parameters);
	 * 
	 * }
	 * 
	 * };
	 * 
	 * button1.setOutputMarkupId(true); form.add(button1); return button1; }
	 */

}
