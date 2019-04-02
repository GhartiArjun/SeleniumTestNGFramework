package com.functionalautomation.excle;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcleMain {

	private XSSFSheet excleSheet;
	private XSSFWorkbook excleWorkbook;
	private FileInputStream fileInpute;
	private String excleLoc = "./src/main/java/com/functionalautomation/testdata/testingData.xlsx";

	public Object getCellValue(String sheetName, int row, int col)  {
		try {

			fileInpute = new FileInputStream(new File(excleLoc));
			excleWorkbook = new XSSFWorkbook(fileInpute);

		}catch (Exception e) {
			e.printStackTrace();
		}
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
		int totalRow = excleSheet.getPhysicalNumberOfRows();
		int totalColumn = excleSheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(totalColumn);
		System.out.println(totalColumn);
		data = new String[totalRow-1][totalColumn];
		
		

		Iterator<Row> rowIterator = excleSheet.iterator();
		int i =0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			int j=0;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();


				if (row.getCell(0).getStringCellValue().contains("name")) {
					break;
					
				}

				switch (cell.getCellTypeEnum()) {
				case NUMERIC:
					cell.getNumericCellValue();
					int ends = String.valueOf(cell.getNumericCellValue()).indexOf(".", 0);
					data[i][j++] = String.valueOf(cell.getNumericCellValue()).substring(0, ends);
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
					data[i][j++] = cell.getStringCellValue();
					//System.out.println(data[i][j++]);
					

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
		} 
		fileInpute.close();
		excleWorkbook.close();
		return data;
	}

	public Object printExcleData(String sheetName) {

		Object data[][] = null;

		try {
			fileInpute = new FileInputStream(new File(excleLoc));
			excleWorkbook = new XSSFWorkbook(fileInpute);
			excleSheet = excleWorkbook.getSheet(sheetName);

			int totalRow = excleSheet.getPhysicalNumberOfRows();
			int totalColumn = excleSheet.getRow(0).getPhysicalNumberOfCells();
			data = new Object[totalRow-1][totalColumn];

			for(int i =1; i<totalRow; i++) {

				for(int j=0; j<totalColumn; j++) {
					
					XSSFCell cell = excleSheet.getRow(i).getCell(j);
					
					switch(cell.getCellTypeEnum()) {
					
					case NUMERIC:
						data[i-1][j]= excleSheet.getRow(i).getCell(j).getNumericCellValue();
						System.out.println(data[i-1][j]);
						break;
					case BOOLEAN:
						data[i-1][j]= excleSheet.getRow(i).getCell(j).getBooleanCellValue();
						break;
					case STRING:
						data[i-1][j]= excleSheet.getRow(i).getCell(j).getStringCellValue();
						break;
					default:
						break;
					}
					
				}

			}
			fileInpute.close();
			excleWorkbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		ExcleMain data = new ExcleMain();
		 Object values = data.printExcleData("Sheet1");

	}

}
