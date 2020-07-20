package com.spring.batch.job.model.service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.spring.batch.job.model.bean.Transaction;

public class ExportFileService {
	
	public static void exportToExcel(List<Transaction> transactionList) throws Exception {
		String excelFilePath = "D:\\backup\\dataSource_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
		
		if(CollectionUtils.isNotEmpty(transactionList)) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Reviews");
 
            writeHeaderLine(sheet);
 
            writeDataLines(transactionList, workbook, sheet);
 
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
 
		}
	}
	
    private static void writeHeaderLine(XSSFSheet sheet) {
    	 
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Trx ID");
 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Account Number");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Description");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Trx Date");
 
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Trx Amount");
        
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Customer ID");
    }
 
    private static void writeDataLines(List<Transaction> transactionList, XSSFWorkbook workbook,
            XSSFSheet sheet)  {
        int rowCount = 1;
 
        for(Transaction transaction: transactionList) {
            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(transaction.getTransactionId().toString());
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(transaction.getAccountNumber());
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(transaction.getDescription());
            
            
            cell = row.createCell(columnCount++);
 
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
 
            cell.setCellValue(transaction.getTrxDate());
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(String.valueOf(transaction.getTrxAmount()));
 
            cell = row.createCell(columnCount);
            cell.setCellValue(transaction.getCustomerId());
 
        }
    }
}
