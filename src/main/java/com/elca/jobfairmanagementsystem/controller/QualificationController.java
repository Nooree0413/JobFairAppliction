package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.exception.QualificationNotFoundException;
import com.elca.jobfairmanagementsystem.service.QualificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<QualificationDto>> getAllQualifications() throws QualificationNotFoundException {
        return new ResponseEntity<>(qualificationService.findAllQualifications(), HttpStatus.OK);
    }

    @GetMapping("/{qualificationId}")
    public ResponseEntity<QualificationDto> getQualificationById(@PathVariable Long qualificationId) throws QualificationNotFoundException {
        return new ResponseEntity<>(qualificationService.findByQualificationId(qualificationId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveQualification(@RequestBody QualificationDto qualificationDto) {
        qualificationService.saveQualification(qualificationDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{qualificationId}")
    public ResponseEntity updateQualification(@RequestBody QualificationDto qualificationDto) throws QualificationNotFoundException {
        qualificationService.updateQualification(qualificationDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{qualificationId}")
    public ResponseEntity deleteQualification(@PathVariable Long qualificationId) throws QualificationNotFoundException {
        qualificationService.deleteQualification(qualificationId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
