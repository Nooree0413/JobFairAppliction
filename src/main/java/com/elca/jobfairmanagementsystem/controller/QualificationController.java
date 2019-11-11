package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.service.QualificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/qualification")
public class QualificationController {

    private final QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<QualificationDto>> getAllQualifications(){
        return new ResponseEntity<>(qualificationService.findAllQualifications(), HttpStatus.FOUND);
    }

}
