package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateFileDto;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.FileNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateFileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate-file")
public class CandidateFileController {
    private final CandidateFileService candidateFileService;

    public CandidateFileController(CandidateFileService candidateFileService) {
        this.candidateFileService = candidateFileService;
    }

    @PostMapping()
    public ResponseEntity saveNewCandidateCv(@RequestPart("candidateId") Long candidateId, @RequestPart("file") MultipartFile file) throws FileNotFoundException, IOException {
        candidateFileService.saveCandidateCv(file,candidateId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<Resource> getCandidateCvById(@PathVariable Long candidateId) throws CandidateNotFoundException {
        CandidateFileDto candidateCv = candidateFileService.getCandidateFileById(candidateId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(candidateCv.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + candidateCv.getFileName() + "\"")
                .body(new ByteArrayResource(candidateCv.getData()));
    }
}
