package com.myfinishproject.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 4178860760772277699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String modelo;
	@Transient
	private boolean answer;
	private Date dataSaida;
	private Date dataRetorno;
	private TipoEnfesto tipoEnfesto;
	private String largura;
	private Status status;

	@ManyToOne
	@JoinColumn(name = "idColecao")
	private Colecao idColecao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public TipoEnfesto getTipoEnfesto() {
		return tipoEnfesto;
	}

	public void setTipoEnfesto(TipoEnfesto tipoEnfesto) {
		this.tipoEnfesto = tipoEnfesto;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Colecao getIdColecao() {
		return idColecao;
	}

	public void setIdColecao(Colecao idColecao) {
		this.idColecao = idColecao;
	}

}
