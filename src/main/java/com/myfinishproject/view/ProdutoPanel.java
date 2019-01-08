package com.myfinishproject.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.myfinishproject.model.Produto;
import com.myfinishproject.model.Status;
import com.myfinishproject.model.TipoEnfesto;

public class ProdutoPanel extends Panel {

	private static final long serialVersionUID = -4953874588297206972L;

	public ProdutoPanel(String id) {
		this(id, new Produto());
	}

	public ProdutoPanel(String id, final Produto produto) {
		super(id);

		Form<Produto> formProduto = new Form<>("formProduto", new CompoundPropertyModel<Produto>(produto));

		final TextField<String> modelo = new TextField<String>("modelo");
		final TextField<String> largura = new TextField<String>("largura");

		modelo.setOutputMarkupId(true);
		largura.setOutputMarkupId(true);

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

		// Butao de slavar
		AjaxButton salvarButton = new AjaxButton("salvarTudo") {

			private static final long serialVersionUID = 958818239380093777L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				executarAoSalvar(target, produto);

				target.add(modelo);
				target.add(largura);
			}
		};
		salvarButton.setOutputMarkupId(true);
		add(formProduto);
		formProduto.add(modelo);
		formProduto.add(largura);
		formProduto.add(tipoEnfesto);
		formProduto.add(status);
		formProduto.add(status);
		formProduto.add(tipoEnfesto);
		formProduto.add(salvarButton);

	}

	// Enviando os dados para a classe produtoForm
	public void executarAoSalvar(AjaxRequestTarget target, Produto produto) {

	}

}
