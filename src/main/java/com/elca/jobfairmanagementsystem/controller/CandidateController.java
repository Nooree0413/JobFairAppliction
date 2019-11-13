package com.elca.jobfairmanagementsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.service.CandidateService;

/**
 *
 * @author ghr
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate/")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    //get all candidate
    @GetMapping("all")
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return new ResponseEntity<>(candidateService.searchAllCandidate(), HttpStatus.FOUND);
    }

    //add new candidate
    @PostMapping("add")
    public ResponseEntity saveNewCandidate(@RequestBody CandidateDto candidateDto) {
        candidateService.saveCandidate(candidateDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //update candidate
    @PutMapping("update")
    public ResponseEntity updateCandidate(@RequestBody CandidateDto candidateDto) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
