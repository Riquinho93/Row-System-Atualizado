package excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myfinishproject.model.Produto;

public class RelatorioExcel{
	
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
//	ByteArrayOutputStream filleOut;

	public ByteArrayOutputStream gerarRelatorio(List<Produto> users) {

		final String[] colunas = { "Modelo", "Largura", "Faccionista", "Cortador", "Data de saida", "Data de retorno",
				"Status" };

		// Criando arquivo execel com os dados do produtoModel
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();

		// definindo tamanho e fonte
		Font headerFont = workbook.createFont();

		headerFont.setFontHeightInPoints((short) 17);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		// Associando a fonte com produtoModel(Workbook)
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Criando o header
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < colunas.length; i++) {
			XSSFCell cell = (XSSFCell) headerRow.createCell(i);
			cell.setCellValue(colunas[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Criando as rows com Produto
		int rowNum = 1;
		
		for (Produto lista : users) {
			XSSFRow row = sheet.createRow(rowNum++);
			Produto aux = new Produto();
			row.createCell(0).setCellValue(lista.getModelo());
			row.createCell(1).setCellValue(lista.getLargura());
			row.createCell(2).setCellValue(lista.getFaccionista());
			row.createCell(3).setCellValue(lista.getCortador());
			row.createCell(4).setCellValue(lista.getDataSaida());
			row.createCell(5).setCellValue(lista.getDataRetorno());
			row.createCell(6).setCellValue(lista.getStatus().converterParaString(1));
			System.out.println("teste: " + lista.getStatus().converterParaString(0));
			System.out.println("Id: " + lista.getStatus());
		}

		// Tamanho das colunas
		for (int i = 0; i < colunas.length; i++) {
			sheet.autoSizeColumn(i);
		}
		ByteArrayOutputStream filleOut = new ByteArrayOutputStream();
		try {
			
			workbook.write(filleOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filleOut;

	}

}
