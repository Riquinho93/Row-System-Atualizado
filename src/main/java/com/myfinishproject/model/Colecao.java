package com.myfinishproject.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "colecao")
public class Colecao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer colecaoId;

	private String nome;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Transient
	private boolean answer;

	@OneToMany(mappedBy = "colecao", targetEntity = Produto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Produto> listaProdutos;

	public Colecao() {
	}

	public Integer getColecaoId() {
		return colecaoId;
	}

	public void setColecaoId(Integer colecaoId) {
		this.colecaoId = colecaoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public Collection<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(Collection<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
