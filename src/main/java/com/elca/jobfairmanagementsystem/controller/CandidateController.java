package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.dto.CandidatePaginationDto;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ghr
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    //get all candidate
    @GetMapping("/all")
    public ResponseEntity<CandidatePaginationDto> getAllCandidates(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws CandidateNotFoundException {
        return new ResponseEntity<>(candidateService.findAllCandidate(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewCandidate(@RequestBody CandidateDto candidateDto) {
        candidateService.saveCandidate(candidateDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{candidateId}")
    public ResponseEntity updateCandidate(@RequestBody CandidateDto candidateDto) throws CandidateNotFoundException {
        candidateService.updateCandidate(candidateDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDto> getOneCandidate(@PathVariable Long candidateId) throws CandidateNotFoundException {
        return new ResponseEntity<>(candidateService.findCandidateById(candidateId), HttpStatus.OK);
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity deleteCandidate(@PathVariable Long candidateId) throws CandidateNotFoundException {
        candidateService.deleteCandidate(candidateId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/candidates/{venueId}")
    public ResponseEntity<List<CandidateDto>> getCandidateByVenueId(@PathVariable Long venueId) throws CandidateNotFoundException{
        return new ResponseEntity<>(candidateService.findCandidateByVenueId(venueId), HttpStatus.OK);
    }

    @PostMapping("/candidate-cv")
    public ResponseEntity saveNewCandidateCv(@RequestPart CandidateDto candidateDto, @RequestPart("file")MultipartFile[] files) throws IOException {
        candidateService.saveCandidateCv(candidateDto,files);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
