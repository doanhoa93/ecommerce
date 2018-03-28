package com.framgia.helper;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class ExcelReportView extends AbstractXlsView {

	private int headerNum = 0;

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"report.xls\"");
		ArrayList<
		    ArrayList<String>> newRecords = (ArrayList<ArrayList<String>>) model.get("newRecords");
		ArrayList<ArrayList<String>> sales = (ArrayList<ArrayList<String>>) model.get("sales");
		ArrayList<ArrayList<String>> orders = (ArrayList<ArrayList<String>>) model.get("orders");
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Ecommerce Report");

		HSSFRow row = sheet.createRow(headerNum++);
		row.createCell(0).setCellValue("New Records");
		addDataToSheet(sheet, newRecords);
		row = sheet.createRow(headerNum++);
		row.createCell(0).setCellValue("Sales");
		addDataToSheet(sheet, sales);
		row = sheet.createRow(headerNum++);
		row.createCell(0).setCellValue("Orders");
		addDataToSheet(sheet, orders);
	}

	private void addDataToSheet(HSSFSheet sheet, ArrayList<ArrayList<String>> report) {
		for (ArrayList<String> elements : report) {
			HSSFRow row = sheet.createRow(headerNum++);
			int colNum = 0;
			for (String element : elements) {
				row.createCell(colNum++).setCellValue(element);
			}
		}

		sheet.createRow(headerNum++);
	}
}
