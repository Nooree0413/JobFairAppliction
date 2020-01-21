package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobCountAllDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobPaginationDto;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateVenueJobService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate-venue-job")
public class CandidateVenueJobController {
    private final CandidateVenueJobService candidateVenueJobService;

    public CandidateVenueJobController(CandidateVenueJobService candidateVenueJobService) {
        this.candidateVenueJobService = candidateVenueJobService;
    }

    @GetMapping("/all")
    public ResponseEntity<CandidateVenueJobPaginationDto> getAllCandidateVenueJobs(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws CandidateVenueJobNotFoundException {
        return new ResponseEntity<>(candidateVenueJobService.findAllCandidateVenueJobs(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @GetMapping("/{candidateVenueJobId}")
    public ResponseEntity<CandidateVenueJobDto> getCandidateVenueJobById(@PathVariable Long candidateVenueJobId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobById(candidateVenueJobId), HttpStatus.OK);
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

    @GetMapping("/candidates/{venueId}/{pageNumber}/{pageSize}")
    public ResponseEntity <CandidateVenueJobPaginationDto> getCandidateByVenueId(@PathVariable Long venueId, @PathVariable Integer pageNumber, @PathVariable Integer pageSize) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findAllCandidateByVenueId(venueId,PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @GetMapping("/count-candidates/{venueId}")
    public ResponseEntity<CandidateVenueJobCountAllDto> getCountCandidateByVenueId(@PathVariable Long venueId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.countCandidatesByVenue(venueId), HttpStatus.OK);
    }

    @GetMapping("/candidates/lastname/{lastName}")
    public ResponseEntity<List<CandidateVenueJobDto>> getCandidateByLastName(@PathVariable String lastName) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/candidates-desc/{venueId}")
    public ResponseEntity<List<CandidateVenueJobDto>> getCandidateByVenueIdDESC(@PathVariable Long venueId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByDESC(venueId), HttpStatus.OK);
    }

    @GetMapping("/candidates-asc/{venueId}")
    public ResponseEntity<List<CandidateVenueJobDto>> getCandidateByVenueIdASC(@PathVariable Long venueId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByASC(venueId), HttpStatus.OK);
    }
}
