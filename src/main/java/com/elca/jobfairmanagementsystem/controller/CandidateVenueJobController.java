package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateVenueJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate-venue-job")
public class CandidateVenueJobController {
    private final CandidateVenueJobService candidateVenueJobService;

    CandidateVenueJobController(CandidateVenueJobService candidateVenueJobService) {
        this.candidateVenueJobService = candidateVenueJobService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateVenueJobDto>> getAllCandidateVenueJobs() throws CandidateVenueJobNotFoundException {
        return new ResponseEntity<>(candidateVenueJobService.findAllCandidateVenueJobs(), HttpStatus.OK);
    }

    @GetMapping("/{candidateVenueJobId}")
    public ResponseEntity<CandidateVenueJobDto> getCandidateVenueJobById(@PathVariable Long candidateVenueJobId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobById(candidateVenueJobId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveCandidateVenueJob(@RequestBody CandidateVenueJobDto candidateVenueJobDto) {
        candidateVenueJobService.saveCandidateVenueJob(candidateVenueJobDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{candidateVenueJobId}")
    public ResponseEntity updateCandidateVenueJob(@RequestBody CandidateVenueJobDto candidateVenueJobDto) throws CandidateVenueJobNotFoundException{
        candidateVenueJobService.updateCandidateVenueJob(candidateVenueJobDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{candidateVenueJobId}")
    public ResponseEntity deleteCandidateVenueJob(@PathVariable Long candidateVenueJobId) throws CandidateVenueJobNotFoundException{
        candidateVenueJobService.deleteCandidateVenueJob(candidateVenueJobId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
