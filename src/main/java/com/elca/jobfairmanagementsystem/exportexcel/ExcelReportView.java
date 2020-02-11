package com.elca.jobfairmanagementsystem.exportexcel;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.dto.ExcelDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=\"candidate.xls\"");
        List<ExcelDto> excelDtos = (List<ExcelDto>) model.get("excelDtos");
        Sheet sheet = workbook.createSheet("Candidate Data");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Registration Date");
        header.createCell(1).setCellValue("First Name");
        header.createCell(2).setCellValue("Last Name");
        header.createCell(3).setCellValue("Venue Name");

        int rowNum = 1;
        for(ExcelDto excelDto:excelDtos){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(excelDto.getRegistrationDate().toString());
            row.createCell(1).setCellValue(excelDto.getFirstName());
            row.createCell(2).setCellValue(excelDto.getLastName());
            row.createCell(3).setCellValue(excelDto.getVenueName());
        }
    }
}
