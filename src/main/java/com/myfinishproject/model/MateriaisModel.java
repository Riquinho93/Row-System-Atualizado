package com.myfinishproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.mail.imap.protocol.UID;

@Entity
@Table(name = "tbMateriais")
public class MateriaisModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long materiaisId;
	private UID iddMateriais;
	private String nome;
	private int qtd = 1;
	private String medida;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMateriaisId() {
		return materiaisId;
	}

	public void setMateriaisId(Long materiaisId) {
		this.materiaisId = materiaisId;
	}

	public UID getIddMateriais() {
		return iddMateriais;
	}

	public void setIddMateriais(UID iddMateriais) {
		this.iddMateriais = iddMateriais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
