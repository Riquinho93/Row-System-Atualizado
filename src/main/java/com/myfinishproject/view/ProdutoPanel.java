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
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.myfinishproject.model.Adicional;
import com.myfinishproject.model.Material;
import com.myfinishproject.model.Peca;
import com.myfinishproject.model.Produto;
import com.myfinishproject.model.Servico;
import com.myfinishproject.model.Status;
import com.myfinishproject.model.TipoEnfesto;
import com.myfinishproject.service.PecaService;

public class ProdutoPanel extends Panel {

	private static final long serialVersionUID = -4953874588297206972L;

	private Form<Produto> formProduto;
// Atributos da peca
	@SpringBean(name = "pecaService")
	private PecaService pecaService;

	private Form<Peca> formPeca;
	private WebMarkupContainer listContainerPecas;
	private PageableListView<Peca> listViewPecas;
	private LoadableDetachableModel<List<Peca>> atualizarPecas;
	private List<Peca> listaPecas = new LinkedList<Peca>();
	private Peca peca = new Peca();

// Atributos do Servico
	private Form<Servico> formServico;
	private WebMarkupContainer listContainerServico;
	private PageableListView<Servico> listViewServico;
	private LoadableDetachableModel<List<Servico>> atualizarServico;
	private List<Servico> listaServicos = new LinkedList<>();
	private Servico servico;

// Atributos do Material
	private Form<Material> formMaterial;
	private WebMarkupContainer listaContainerMaterial;
	private PageableListView<Material> listViewMaterial;
	private LoadableDetachableModel<List<Material>> atualizarMaterial;
	private List<Material> listaMateriais = new LinkedList<>();
	private Material material;

// Atributos do Adicional
	private Form<Adicional> formAdicional;
	private WebMarkupContainer listaContainerAdicional;
	private PageableListView<Adicional> listViewAdicionais;
	private LoadableDetachableModel<List<Adicional>> atualizarAdiconais;
	private List<Adicional> listaAdicionais;
	private Adicional adicional;

	public ProdutoPanel(String id) {
		this(id, new Produto());
	}

	public ProdutoPanel(String id, final Produto produto) {
		super(id);

		//Chamada Metodo de Peca
		pecaMetodo();
		add(pecasWebMarkupContainer());
		listaPecas = pecaService.listar();
		//Chamada Metodo de servico
		servicoMetodo();
		add(servicoWebMarkupContainer());
		//Chamada Metodo de Material
		materialMetodo();
		add(materialWebMarkupContainer());
		// Chamada metodo de Adicional
		adicionalMetodo();
		add(adicionalWebMarkupContainer());

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

				executarAoSalvar(target, produto);
				produto.setListaPecas(listaPecas);

				for (Peca lista : listaPecas) {
					System.out.println("Id" + lista.getId() + "Peca2: " + lista.getCor());
					System.out.println("Id" + lista.getId() + "Peca2: " + peca.getCor());
//					peca.setProduto(produto);
//					pecaService.SalvarOuAlterar(peca);
				}

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
				Peca pecaAjax = (Peca) form2.getModelObject();
				listaPecas.add(pecaAjax);
				target.add(listContainerPecas);
				System.out.println("Cor " + pecaAjax.getCor());
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
	private WebMarkupContainer pecasWebMarkupContainer() {
		listContainerPecas = new WebMarkupContainer("containerPecas");
		listContainerPecas.setOutputMarkupId(true);
		atualizarPecas = new LoadableDetachableModel<List<Peca>>() {

			private static final long serialVersionUID = 1L;

			protected List<Peca> load() {
				return listaPecas;
			}
		};

		// Criando a lista View
		listViewPecas = new PageableListView<Peca>("listViewPecas", atualizarPecas, 5) {

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

		listViewPecas.setOutputMarkupId(true);

		// Aparecer no container
		listContainerPecas.add(listViewPecas);

		return listContainerPecas;
	}

	// Metodo do Servico
	private void servicoMetodo() {
		servico = new Servico();
		formServico = new Form<>("formServico", new CompoundPropertyModel<Servico>(servico));

		final TextField<String> tecido = new TextField<>("tecido");
		final TextField<String> cor = new TextField<>("cor");
		final TextField<String> composicao = new TextField<>("composicao");
		final NumberTextField<Integer> quantidade = new NumberTextField<>("quantidade");
		final NumberTextField<Float> valor = new NumberTextField<>("valor");

		tecido.setOutputMarkupId(true);
		cor.setOutputMarkupId(true);
		composicao.setOutputMarkupId(true);
		quantidade.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("addServico") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Servico servico = (Servico) form.getModelObject();
				listaServicos.add(servico);
				target.add(listContainerServico);
			}
		};
		button.setOutputMarkupId(true);

		formServico.add(tecido);
		formServico.add(cor);
		formServico.add(composicao);
		formServico.add(quantidade);
		formServico.add(valor);
		formServico.add(button);
		add(formServico);
	}

	private WebMarkupContainer servicoWebMarkupContainer() {

		listContainerServico = new WebMarkupContainer("containerServico");
		listContainerServico.setOutputMarkupId(true);
		atualizarServico = new LoadableDetachableModel<List<Servico>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Servico> load() {
				return listaServicos;
			}
		};
		listViewServico = new PageableListView<Servico>("listViewServico", atualizarServico, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Servico> item) {
				Servico user = item.getModelObject();

				item.add(new Label("tecido", user.getTecido()));
				item.add(new Label("cor", user.getCor()));
				item.add(new Label("composicao", user.getComposicao()));
				item.add(new Label("quantidade", user.getQuantidade()));
				item.add(new Label("valor", user.getValor()));
			}
		};
		listViewServico.setOutputMarkupId(true);
		listContainerServico.add(listViewServico);

		return listContainerServico;
	}

	// Metodo do Material
	private void materialMetodo() {
		material = new Material();
		formMaterial = new Form<>("formMaterial", new CompoundPropertyModel<Material>(material));

		final TextField<Material> material = new TextField<>("material");
		final TextField<Material> medida = new TextField<>("medida");
		final NumberTextField<Integer> quantidade = new NumberTextField<>("quantidade");

		material.setOutputMarkupId(true);
		medida.setOutputMarkupId(true);
		quantidade.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("addMaterial") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Material material = (Material) form.getModelObject();
				listaMateriais.add(material);
				target.add(listaContainerMaterial);
			}
		};
		button.setOutputMarkupId(true);
		formMaterial.add(material);
		formMaterial.add(medida);
		formMaterial.add(quantidade);
		formMaterial.add(button);
		add(formMaterial);

	}

	private WebMarkupContainer materialWebMarkupContainer() {
		listaContainerMaterial = new WebMarkupContainer("containerMaterial");
		listaContainerMaterial.setOutputMarkupId(true);
		atualizarMaterial = new LoadableDetachableModel<List<Material>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Material> load() {
				return listaMateriais;
			}
		};

		listViewMaterial = new PageableListView<Material>("listViewMaterial", atualizarMaterial, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Material> item) {
				Material user = item.getModelObject();
				item.add(new Label("material", user.getMaterial()));
				item.add(new Label("quantidade", user.getQuantidade()));
				item.add(new Label("medida", user.getMedida()));
			}
		};
		listViewMaterial.setOutputMarkupId(true);
		listaContainerMaterial.add(listViewMaterial);

		return listaContainerMaterial;
	}

	// Metodo do Adicional
	private void adicionalMetodo() {
		adicional = new Adicional();
		formAdicional = new Form<>("formAdicional", new CompoundPropertyModel<Adicional>(adicional));

		final TextField<Adicional> adicional = new TextField<>("adicional");
		final TextArea<Adicional> descricao = new TextArea<>("descricao");

		adicional.setOutputMarkupId(true);
		descricao.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("addAdicional") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Adicional adicional = (Adicional) form.getModelObject();
				listaAdicionais.add(adicional);
				target.add(listaContainerAdicional);
				
				/*
				 * 
				 * ERRO AQUI NESTA PARTE
				 * 
				 * */
				
				
			}
		};
		button.setOutputMarkupId(true);
		formAdicional.add(adicional);
		formAdicional.add(descricao);
		formAdicional.add(button);
		add(formAdicional);
	}

	private WebMarkupContainer adicionalWebMarkupContainer() {
		listaContainerAdicional = new WebMarkupContainer("containerAdicional");
		listaContainerAdicional.setOutputMarkupId(true);
		atualizarAdiconais = new LoadableDetachableModel<List<Adicional>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Adicional> load() {
				return listaAdicionais;
			}
		};

		listViewAdicionais = new PageableListView<Adicional>("listViewAdicionais", atualizarAdiconais, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Adicional> item) {
				Adicional user = item.getModelObject();
				item.add(new Label("adicional", user.getAdicional()));
				item.add(new Label("descricao", user.getDescricao()));
			}
		};
		listViewAdicionais.setOutputMarkupId(true);
		listaContainerAdicional.add(listViewAdicionais);
		return listaContainerAdicional;
	}
}
