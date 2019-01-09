package com.myfinishproject.view;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.myfinishproject.model.Peca;
import com.myfinishproject.model.Produto;
import com.myfinishproject.model.Status;
import com.myfinishproject.model.TipoEnfesto;
import com.myfinishproject.service.PecaService;

public class ProdutoPanel extends Panel {

	private static final long serialVersionUID = -4953874588297206972L;
	
	@SpringBean(name = "pecaService")
	private PecaService pecaService;
	
	private Form<Peca> formPeca;
	private WebMarkupContainer listContainerPecas;
	private PageableListView<Peca> listViewPecas;
	private LoadableDetachableModel<List<Peca>> atualizarPecas;
	private List<Peca> listaPecas = new LinkedList<Peca>();
	private Peca peca;
	private Form<Produto> formProduto ;
	private Produto idProduto = new Produto();
	
	public ProdutoPanel(String id) {
		this(id, new Produto());
	}

	public ProdutoPanel(String id, final Produto produto) {
		super(id);
		idProduto = produto;
		System.out.println("Produto: " + produto.getId());
		listaPecas = pecaService.listar();
		//Metodo de Peca
		pecaMetodo();
		add(PecasWebMarkupContainer());

		formProduto = new Form<>("formProduto", new CompoundPropertyModel<Produto>(produto));

		final TextField<String> modelo = new TextField<String>("modelo");
		final TextField<String> largura = new TextField<String>("largura");
		final TextField<String> faccionista = new TextField<String>("faccionista");
		final TextField<String> cortador = new TextField<String>("cortador");

		modelo.setOutputMarkupId(true);
		largura.setOutputMarkupId(true);
		faccionista.setOutputMarkupId(true);
		cortador.setOutputMarkupId(true);

		// Botão de status
		ChoiceRenderer<Status> choiceRenderer = new ChoiceRenderer<Status>("descricaoStatus");
		IModel<List<Status>> model2 = new LoadableDetachableModel<List<Status>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<Status> load() {
				return Status.status();
			}
		};

		DropDownChoice<Status> status = new DropDownChoice<>("status", model2, choiceRenderer);

		// Botão do tipo enfesto
		ChoiceRenderer<TipoEnfesto> renderer = new ChoiceRenderer<TipoEnfesto>("descricao");
		IModel<List<TipoEnfesto>> model = new LoadableDetachableModel<List<TipoEnfesto>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<TipoEnfesto> load() {
				return TipoEnfesto.tipo();
			}
		};

		DropDownChoice<TipoEnfesto> tipoEnfesto = new DropDownChoice<>("tipoEnfesto", model, renderer);

		// Data entrada
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

		DateTextField data = new DateTextField("dataSaida", "dd/MM/yyyy");
		datePickerInicial.setAutoHide(true);
		data.add(datePickerInicial);
		data.setOutputMarkupId(true);
		formProduto.add(data);

		// Data Retorno
		DatePicker datePickerRetorno = new DatePicker() {
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

		DateTextField data2 = new DateTextField("dataRetorno", "dd/MM/yyyy");
		datePickerRetorno.setAutoHide(true);
		data2.add(datePickerRetorno);
		data2.setOutputMarkupId(true);
		formProduto.add(data2);

		// Butao de salvar
		AjaxButton salvarButton = new AjaxButton("salvarTudo") {

			private static final long serialVersionUID = 958818239380093777L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				
//				pecaService.SalvarOuAlterar(peca);
				System.out.println("ProdutoSubimit: " + produto.getId());
				executarAoSalvar(target, produto);

				target.add(modelo);
				target.add(largura);
				target.add(faccionista);
				target.add(cortador);
			}
		};
		salvarButton.setOutputMarkupId(true);
		add(formProduto);
		formProduto.add(modelo);
		formProduto.add(largura);
		formProduto.add(faccionista);
		formProduto.add(cortador);
		formProduto.add(tipoEnfesto);
		formProduto.add(status);
		formProduto.add(tipoEnfesto);
		formProduto.add(salvarButton);

	}

	// Enviando os dados para a classe produtoForm
	public void executarAoSalvar(AjaxRequestTarget target, Produto produto) {

	}
	
	// Aqui começa aba de peças
		// Metodo Peças
		private void pecaMetodo() {

			peca = new Peca();
			CompoundPropertyModel<Peca> compoundPropertyModel = new CompoundPropertyModel<Peca>(peca);
			formPeca = new Form<Peca>("formPeca", compoundPropertyModel);

			final TextField<Peca> cor = new TextField<Peca>("cor");
			final TextField<Peca> tamanho = new TextField<Peca>("tamanho");
			final NumberTextField<Integer> quantidade = new NumberTextField<Integer>("quantidade");
			
			cor.setOutputMarkupId(true);
			tamanho.setOutputMarkupId(true);
			quantidade.setOutputMarkupId(true);

			AjaxButton ajaxButton = new AjaxButton("addPeca") {

				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form2) {
					super.onSubmit(target, form2);
					Peca coresAjax = (Peca) form2.getModelObject();
					listaPecas.add(coresAjax);
					target.add(listContainerPecas);
//					peca.setIdProduto(idProduto);
					System.out.println("IdProduto: " + idProduto.getId());
					target.add(cor);
					target.add(tamanho);
					target.add(quantidade);
				}
			};
			ajaxButton.setOutputMarkupId(true);

//			formProduto.add(formPeca);
			add(formPeca);
			formPeca.add(cor);
			formPeca.add(tamanho);
			formPeca.add(quantidade);
			formPeca.add(ajaxButton);

		}

		// ListView
		private WebMarkupContainer PecasWebMarkupContainer() {
			listContainerPecas = new WebMarkupContainer("containerPecas");
			listContainerPecas.setOutputMarkupId(true);
			 atualizarPecas = new LoadableDetachableModel<List<Peca>>() {

				private static final long serialVersionUID = 1L;

				protected List<Peca> load() {
					return listaPecas;
				}
			};

			// Criando a lista View
			listViewPecas = new PageableListView<Peca>("listViewPecas", atualizarPecas, 3) {

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Peca> item) {

					Peca user = item.getModelObject();

					item.add(new Label("cor", user.getCor()));
					item.add(new Label("tamanho", user.getTamanho()));
					item.add(new Label("quantidade", user.getQuantidade()));
//					item.add(removendo(user));
				}

			};
			listViewPecas.setReuseItems(true);

			listViewPecas.setOutputMarkupId(true);

			// Aparecer no container
			listContainerPecas.add(listViewPecas);

			return listContainerPecas;
		}

}
