package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;
import com.elca.jobfairmanagementsystem.entity.CandidateScreening;
import com.elca.jobfairmanagementsystem.service.CandidateScreeningService;
import com.elca.jobfairmanagementsystem.service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate-screening")
public class CandidateScreeningController {
    private final CandidateScreeningService candidateScreeningService;

    public CandidateScreeningController(CandidateScreeningService candidateScreeningService){
        this.candidateScreeningService = candidateScreeningService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateScreeningDto>> getAllCandidateScreenings() {
        return new ResponseEntity<>(candidateScreeningService.findAllCandidateScreening(), HttpStatus.FOUND);
    }

    @GetMapping("/{candidateScreeningId}")
    public ResponseEntity<CandidateScreeningDto> getCandidateScreeningById(@PathVariable Long candidateScreeningId) {
        return new ResponseEntity<>(candidateScreeningService.findByCandidateScreeningId(candidateScreeningId), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity saveNewCandidateScreening(@RequestBody CandidateScreeningDto candidateScreeningDto) {
        candidateScreeningService.saveCandidateScreening(candidateScreeningDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{candidateScreeningId}")
    public ResponseEntity updateCandidateScreening(@RequestBody CandidateScreeningDto candidateScreeningDto) {
        candidateScreeningService.updateCandidateScreening(candidateScreeningDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
