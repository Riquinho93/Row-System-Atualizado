package com.myfinishproject.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "colecao")
public class Colecao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer colecaoId;

	private String nome;

	private String dtEntrada;
	@Transient
	private boolean answer;
	
	@OneToMany(mappedBy = "colecao", targetEntity = Produto.class , fetch =  FetchType.LAZY)
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

	public String getDtEntrada() {
		return dtEntrada;
	}

	public void setDtEntrada(String dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

	public Collection<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(Collection<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

}
