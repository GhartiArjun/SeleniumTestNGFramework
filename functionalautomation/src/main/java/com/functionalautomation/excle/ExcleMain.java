package com.functionalautomation.excle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcleMain {
	private XSSFSheet excleSheet;
	private XSSFWorkbook excleWorkbook;
	private FileInputStream fileInpute;
	private String excleLoc = "./src/main/java/com/functionalautomation/testdata/sampleData.xlsx";

	public Object getCellValue(String sheetName, int row, int col) throws IOException {
		
		fileInpute = new FileInputStream(new File(excleLoc));
		excleWorkbook = new XSSFWorkbook(fileInpute);
		excleSheet = excleWorkbook.getSheet(sheetName);
		int rowLength = excleSheet.getLastRowNum();
		int colLength = excleSheet.getRow(0).getLastCellNum()-1;
		
		
		if ((!(row<0) &&!(row>rowLength))&&(!(col<0) &&!(col>colLength))) {
			return excleSheet.getRow(row).getCell(col);
		} 
			return "invalid parameter! plese make sure your parameters are correct.";
	}

	public String[][] readExcle(String sheetName) throws IOException {
		String data[][] = null;

		fileInpute = new FileInputStream(new File(excleLoc));
		excleWorkbook = new XSSFWorkbook(fileInpute);
		excleSheet = excleWorkbook.getSheet(sheetName);
		int totalRow = excleSheet.getLastRowNum();
		int totalColumn = excleSheet.getRow(0).getLastCellNum();
		data = new String[totalRow][totalColumn];

		Iterator<Row> rowIterator = excleSheet.iterator();
		int i = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			int j = 0;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				if (row.getCell(0).getStringCellValue().contains("name")) {
					break;
				}

				switch (cell.getCellTypeEnum()) {
				case NUMERIC:
					cell.getNumericCellValue();
					int end = String.valueOf(cell.getNumericCellValue()).indexOf(".", 0);
					data[i][j] = String.valueOf(cell.getNumericCellValue()).substring(0, end);
					//System.out.println(data[i][j]);
					/*
					 * int end = String.valueOf(cell.getNumericCellValue()).indexOf(".", 0);
					 * data[][] = String.valueOf(cell.getNumericCellValue()).substring(0,
					 * end).trim(); System.out.print("|"+data);
					 */
					break;
				case BOOLEAN:
					cell.getBooleanCellValue();
					data[i][j] = String.valueOf(cell.getBooleanCellValue());
					//System.out.println(data[i][j]);
					/*
					 * data = String.valueOf(cell.getBooleanCellValue());
					 * System.out.print("|"+data);
					 */
					break;
				case STRING:
					cell.getStringCellValue();
					data[i][j] = cell.getStringCellValue();
					//System.out.println(data[i][j]);
					/*
					 * data = cell.getStringCellValue(); System.out.print("|"+data);
					 */
					break;
				case ERROR:
					cell.getErrorCellValue();
					data[i][j] = cell.getStringCellValue();
					/* data = String.valueOf(cell.getErrorCellValue()); */
					break;
				default:
					break;
				}
			}
			System.out.println();
		}
		fileInpute.close();
		excleWorkbook.close();
		return data;
	}

	public String getValueFromExcle() throws IOException {
		String values[][] = readExcle("sample");
			return values[0][1];
	}

	public  void writeExcel(String sheetName,String status) throws IOException {

		fileInpute = new FileInputStream(new File(excleLoc));
		excleWorkbook = new XSSFWorkbook(fileInpute);
		excleSheet = excleWorkbook.getSheet(sheetName);

		Iterator<Row> rowIterator = excleSheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				if(cell.getStringCellValue().contains("Status")) {
					
					for(int i = 1; i <excleSheet.getLastRowNum(); i ++) {
						cell.getAddress();
						cell.setCellValue(status);
					}
					FileOutputStream output = new FileOutputStream(excleLoc);
					excleWorkbook.write(output);
					output.flush();
					excleWorkbook.close();
				}

			}
		}
	}

	public static void main(String[] args) throws IOException {
		ExcleMain main = new ExcleMain();
		//main.getValueFromExcle();
		// main.readExcle("sample");
		//String[][] values = main.readExcle("sample");
		//System.out.println(values[0][1]);
		//main.writeExcel("sample");
		//System.out.println("msg " + fromexcel[1][1]);
		main.writeExcel("sample","PASS");
		
	}
	
	

}
