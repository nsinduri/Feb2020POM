package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public static Workbook book;
	public static Sheet sheet;
	
	public static String TESTDATA_SHEET_PATH = "./src/main/java/com/qa/hubspot/TestData/TestData.xls";
	
	public static Object[][] getTestData(String sheetname)
	{
	 Object data[][] = null;	
	 FileInputStream ip = null;
	 
	try {
		ip = new FileInputStream(TESTDATA_SHEET_PATH);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		book = WorkbookFactory.create(ip);
	} catch (InvalidFormatException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	sheet = book.getSheet(sheetname);
	
	data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	
	for(int i =0;i<sheet.getLastRowNum();i++)
	{
		for(int j=0; j<sheet.getRow(0).getLastCellNum();j++)
		{
			data[i][j]= sheet.getRow(i+1).getCell(j).toString();
		}
		
	}
	
	return data;
	
	
	
	}	
	
}
