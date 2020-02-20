package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.*;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.exportexcel.ExcelReportView;
import com.elca.jobfairmanagementsystem.service.CandidateService;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/excel")
public class ExcelController {
    private final CandidateService candidateService;
    private final VenueJobService venueJobService;

    public ExcelController(CandidateService candidateService, VenueJobService venueJobService) {
        this.candidateService = candidateService;
        this.venueJobService = venueJobService;
    }

    @GetMapping("/export")
    public ResponseEntity<DownloadDto> getExcel() throws CandidateNotFoundException {
        CandidatePaginationDto candidatePaginationDto = candidateService.findAllCandidate(PageRequest.of(0, 1000));
        List<ExcelDto> excelDtos = new ArrayList<>();
        List<CandidateDto> candidateDtos = candidatePaginationDto.getCandidateDtoList();
        candidateDtos.forEach(candidateDto -> {
            candidateDto.getCandidateVenueJobSaveDto().forEach(candidateVenueJobSaveDto -> {
                VenueJobDto venueJobDto = null;
                try {
                    venueJobDto = venueJobService.findVenueJobById(candidateVenueJobSaveDto.getVenueJobId());
                } catch (VenueJobNotFoundException e) {
                    e.printStackTrace();
                }
                String venueName = venueJobDto.getVenue().getVenueName();
                excelDtos.add(new ExcelDto(candidateDto.getRegistrationDate(), candidateDto.getFirstName(), candidateDto.getLastName(), venueName));
            });
        });

        var file = ExcelReportView.buildExcelDocument(excelDtos);
        DownloadDto downloadDto = null;
        try {
            downloadDto = DownloadDto.builder().file(Files.readAllBytes(file.toPath())).name("candidates.xls").build();
        } catch (IOException e) {
            //log
        }

        return ResponseEntity.ok(downloadDto);
    }
}
