package com.verma.sandeep.hospital.mate.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.entity.Specialization;

@Service
public class SpecializationExcelService {
	
	public byte[] generateExcel(List<Specialization> specializations) throws IOException {
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Specializations");
		// Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Name", "Code", "Description", "Created By", "Updated By"};
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(getHeaderCellStyle(workbook));
        }//for
        
     // Populate data rows
        int rowNum = 1;
        for (Specialization spec : specializations) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getId());
            row.createCell(1).setCellValue(spec.getSpecName());
            row.createCell(2).setCellValue(spec.getSpecCode());
            row.createCell(3).setCellValue(spec.getDescription());
            row.createCell(4).setCellValue(spec.getCreatedBy());
            row.createCell(5).setCellValue(spec.getUpdatedBy());
        }//for
        
        //Convert Workbook to Byte Array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    	
	}
	
	//Creates a cell style with bold text for headers
	private CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

}
