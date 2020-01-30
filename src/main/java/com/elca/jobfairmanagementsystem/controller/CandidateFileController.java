package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateFileDto;
import com.elca.jobfairmanagementsystem.dto.DownloadDto;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.FileNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateFileService;
import lombok.Builder;
import lombok.Data;
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
    public ResponseEntity saveNewCandidateCv(@RequestPart("candidateId") Long candidateId, @RequestPart("file") MultipartFile file,@RequestPart("fileName") String fileName) throws FileNotFoundException, IOException {
        candidateFileService.saveCandidateCv(file,candidateId,fileName);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<DownloadDto> getCandidateCvById(@PathVariable String fileName) throws CandidateNotFoundException {
        CandidateFileDto candidateCv = candidateFileService.getCandidateFileByFileName(fileName);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(candidateCv.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + candidateCv.getFileName() + "\"")
//                .body(new ByteArrayResource(candidateCv.getData()));
        return ResponseEntity.ok(DownloadDto.builder().name(candidateCv.getFileName()).file(candidateCv.getData()).build());
    }

}
