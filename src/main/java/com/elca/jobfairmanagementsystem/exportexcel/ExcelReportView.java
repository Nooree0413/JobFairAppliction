package com.elca.jobfairmanagementsystem.exportexcel;

import com.elca.jobfairmanagementsystem.dto.ExcelDto;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class ExcelReportView {
    public File buildExcelDocument(List<ExcelDto> excelDtos){
        try {
            @Cleanup Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Candidate Data");
            Row header = sheet.createRow(0);
            File file = File.createTempFile("candidates", ".xls");

            header.createCell(0).setCellValue("Registration Date");
            header.createCell(1).setCellValue("First Name");
            header.createCell(2).setCellValue("Last Name");
            header.createCell(3).setCellValue("Venue Name");

            int rowNum = 1;
            for (ExcelDto excelDto : excelDtos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(excelDto.getRegistrationDate().toString());
                row.createCell(1).setCellValue(excelDto.getFirstName());
                row.createCell(2).setCellValue(excelDto.getLastName());
                row.createCell(3).setCellValue(excelDto.getVenueName());
            }

            @Cleanup var fos = new FileOutputStream(file);
            workbook.write(fos);
            return file;
        } catch (IOException ex) {
            //log
            return null;
        }
    }
}
