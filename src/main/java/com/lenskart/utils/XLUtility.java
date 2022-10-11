package com.lenskart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlFile, String xlSheetName) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;

	}

	public static int getCellCount(String xlFile, String xlSheetName, int rowNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheetName);
		row = ws.getRow(rowNum);
		int cloumnCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cloumnCount;

	}

	public static String getCellData(String xlFile, String xlSheet, int rowNum, int coloumnNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(coloumnNum);
		String data;
		try {
			DataFormatter dataFormatter = new DataFormatter();
			data = dataFormatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlFile, String xlSheet, int rowNum, int coloumnNum, String data)
			throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.createCell(coloumnNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();

	}
	
}
