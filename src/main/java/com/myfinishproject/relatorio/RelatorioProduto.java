package com.myfinishproject.relatorio;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class RelatorioProduto implements Serializable{
	private static final long serialVersionUID = 1L;

	public byte[] gerarRelatorio(HashMap<String, Object> produtos) throws JRException {

		// Abrindo o arquivo
		InputStream arq = RelatorioProduto.class.getResourceAsStream("/report/RelatorioProduto.jasper");

		return JasperRunManager.runReportToPdf(arq, produtos, new JREmptyDataSource());

	}
}
