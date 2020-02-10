package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.dto.ExcelDto;
import com.elca.jobfairmanagementsystem.exportexcel.ExcelReportView;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/excel")
public class ExcelController {
    @GetMapping("/export")
    public ModelAndView getExcel(){
        List<ExcelDto> excelDtos = new ArrayList<ExcelDto>();
        excelDtos.add(new ExcelDto((long) 1,"Akash","Bhowaniah"));
        excelDtos.add(new ExcelDto((long) 2,"Awad","Luckoo"));
        return new ModelAndView(new ExcelReportView(),"excelDtos",excelDtos);
    }
}
