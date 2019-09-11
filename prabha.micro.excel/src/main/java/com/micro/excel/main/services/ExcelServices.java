package com.micro.excel.main.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.excel.main.been.CustomerData;
import com.micro.excel.main.utils.FileclsUtil;

@Component
public class ExcelServices {
	
	@Autowired
	FileclsUtil fileclsUtil;
	
	public void writeByte(CustomerData cusData)    { 			
		try {
			String filePath = fileclsUtil.createFolder(cusData.getDateTime())+"/"+cusData.getFileName();
			fileclsUtil.fileWriter( cusData.getBytes(),filePath );
			excelExtractService(filePath,cusData.getFileType());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    } 
	
	public void excelExtractService(String filePath,String type) throws FileNotFoundException, IOException {
		
		Workbook workbook = getWorkBook(filePath, type);
		int totalSheet = workbook.getNumberOfSheets();
		System.out.println("totalSheet  "+totalSheet);
		for(int currentSheet=0; currentSheet<totalSheet; currentSheet++ ) {
			 Sheet sheet = workbook.getSheetAt(currentSheet);
			 int frow = sheet.getFirstRowNum();
			 int lrow = sheet.getLastRowNum();
			 for(int rowno =frow; rowno<lrow; rowno++) {	
				Row row = sheet.getRow(rowno);
				int fcell = row.getFirstCellNum();
				int lcell = row.getLastCellNum();				
				for(int cellNo = fcell; cellNo < lcell; cellNo ++ ) {
					Cell cell = row.getCell(cellNo);
					String data = getValues(cell).toString();

					System.out.print(data +"  -  ");
				}
				
				System.out.println();
			 }
			 
		}
       
	}

	private Workbook getWorkBook(String filePath, String type) throws FileNotFoundException, IOException {
		Workbook workbook = null;		 
		try {
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			if(type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
		         workbook = new XSSFWorkbook(excelFile);
			}else if(type.equals("application/vnd.ms-excel")) {
		         workbook = new HSSFWorkbook(excelFile);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	public Object getValues(Cell cell) {
		try {
			
			if (cell != null) {
				switch (cell.getCellType()) {
				case BOOLEAN:
					return cell.getBooleanCellValue();
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

						return dateFormat.format(cell.getDateCellValue());
					} else {
						return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
					}

				case STRING:
					return cell.getStringCellValue();
				case BLANK:
					return "";
				case ERROR:
					return "";
				case FORMULA:
					switch (cell.getCachedFormulaResultType()) {

					case BOOLEAN:
						return cell.getBooleanCellValue();
					case ERROR:
						return "";
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
							return dateFormat.format(cell.getDateCellValue());
						} else {
							return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();

						}
					case STRING:
						return cell.getRichStringCellValue();
					}
					break;
				}
			} else {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		

}
