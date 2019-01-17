package excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myfinishproject.model.Produto;

public class RelatorioExcel implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
//	ByteArrayOutputStream filleOut;

	public ByteArrayOutputStream gerarRelatorio(Produto user) {

		final String[] colunas = { "Modelo", "Largura", "Tipo Enfesto", "Data de criação", "Data de retorno",
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
		XSSFRow row = sheet.createRow(rowNum++);
		row.createCell(0).setCellValue(user.getModelo());
		row.createCell(1).setCellValue(user.getLargura());
//		row.createCell(2).setCellValue(user.getTipoEnfesto());
		row.createCell(3).setCellValue(user.getDataSaida());
		row.createCell(4).setCellValue(user.getDataRetorno());
//		row.createCell(5).setCellValue(user.getStatus());

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
