package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.*;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.exception.VenueNotFoundException;
import com.elca.jobfairmanagementsystem.exportexcel.ExcelReportView;
import com.elca.jobfairmanagementsystem.service.CandidateService;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import com.elca.jobfairmanagementsystem.service.VenueService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getExcel() throws CandidateNotFoundException {
        CandidatePaginationDto candidatePaginationDto = candidateService.findAllCandidate(PageRequest.of(0,1000));
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
                    excelDtos.add(new ExcelDto(candidateDto.getRegistrationDate(),candidateDto.getFirstName(),candidateDto.getLastName(),venueName));
            });
        });
        return new ModelAndView(new ExcelReportView(),"excelDtos",excelDtos);
    }
}
