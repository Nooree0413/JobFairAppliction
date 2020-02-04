package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;
import com.elca.jobfairmanagementsystem.exception.CandidateScreeningNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate-screening")
public class CandidateScreeningController {
    private final CandidateScreeningService candidateScreeningService;

    public CandidateScreeningController(CandidateScreeningService candidateScreeningService) {
        this.candidateScreeningService = candidateScreeningService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateScreeningDto>> getAllCandidateScreenings() throws CandidateScreeningNotFoundException {
        return new ResponseEntity<>(candidateScreeningService.findAllCandidateScreening(), HttpStatus.OK);
    }

    @GetMapping("/{candidateScreeningId}")
    public ResponseEntity<CandidateScreeningDto> getCandidateScreeningById(@PathVariable Long candidateScreeningId) throws CandidateScreeningNotFoundException {
        return new ResponseEntity<>(candidateScreeningService.findByCandidateScreeningId(candidateScreeningId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewCandidateScreening(@RequestBody CandidateScreeningDto candidateScreeningDto) {
        candidateScreeningService.saveCandidateScreening(candidateScreeningDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{candidateScreeningId}")
    public ResponseEntity updateCandidateScreening(@RequestBody CandidateScreeningDto candidateScreeningDto) throws CandidateScreeningNotFoundException {
        candidateScreeningService.updateCandidateScreening(candidateScreeningDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{candidateScreeningId}")
    public ResponseEntity deleteExperience(@PathVariable Long candidateScreeningId) throws CandidateScreeningNotFoundException {
        candidateScreeningService.deleteCandidateScreening(candidateScreeningId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
