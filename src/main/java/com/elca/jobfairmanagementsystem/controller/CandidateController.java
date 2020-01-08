package com.elca.jobfairmanagementsystem.controller;

import java.util.List;

import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.FileNotFoundException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.service.CandidateService;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<List<CandidateDto>> getAllCandidates() throws CandidateNotFoundException {
        return new ResponseEntity<>(candidateService.findAllCandidate(), HttpStatus.OK);
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

    @PostMapping("/candidates-cv")
    public ResponseEntity saveNewCandidateCv(@RequestPart CandidateDto candidateDto, @RequestPart("file")MultipartFile file) throws FileNotFoundException {
        candidateService.saveCandidateCv(candidateDto,file);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/candidates-cv/{candidateId}")
    public ResponseEntity<Resource> getCandidateCvById(@PathVariable Long candidateId) throws FileNotFoundException{
        var candidateCv = candidateService.findCandidateCvById(candidateId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(candidateCv.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + candidateCv.getFileName() + "\"")
                .body(new ByteArrayResource(candidateCv.getData()));
    }
}
