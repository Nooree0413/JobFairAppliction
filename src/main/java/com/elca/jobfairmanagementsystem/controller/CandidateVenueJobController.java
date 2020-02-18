package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobCountAllDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobPaginationDto;
import com.elca.jobfairmanagementsystem.dto.DashboardDto;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateVenueJobService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR')")
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

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR')")
    @GetMapping("/candidates-by-venue/{venueId}")
    public ResponseEntity <CandidateVenueJobPaginationDto> getCandidateByVenueId(@PathVariable Long venueId, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findAllCandidateByVenueId(venueId,PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR')")
    @GetMapping("/count-candidates/{venueId}")
    public ResponseEntity<CandidateVenueJobCountAllDto> getCountCandidateByVenueId(@PathVariable Long venueId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.countCandidatesByVenue(venueId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_HR')")
    @GetMapping("/candidates/lastname/{lastName}")
    public ResponseEntity<List<CandidateVenueJobDto>> getCandidateByLastName(@PathVariable String lastName) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByLastName(lastName), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_HR')")
    @GetMapping("/candidates-desc/{venueId}")
    public ResponseEntity<List<CandidateVenueJobDto>> getCandidateByVenueIdDESC(@PathVariable Long venueId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByDESC(venueId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_HR')")
    @GetMapping("/candidates-asc/{venueId}")
    public ResponseEntity<List<CandidateVenueJobDto>> getCandidateByVenueIdASC(@PathVariable Long venueId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByASC(venueId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_HR')")
    @GetMapping("/all-candidates-desc")
    public ResponseEntity<CandidateVenueJobPaginationDto> getAllCandidateByDESC(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findAllCandidateVenueJobByDESC(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_HR')")
    @GetMapping("/all-candidates-asc")
    public ResponseEntity<CandidateVenueJobPaginationDto> getAllCandidateByASC(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findAllCandidateVenueJobByASC(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_HR')")
    @GetMapping("/candidates-level/{currentLevel}")
    public ResponseEntity<CandidateVenueJobPaginationDto> getCandidateByCurrentLevel(@PathVariable String currentLevel,@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByCurrentLevel(currentLevel,PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_HR')")
    @GetMapping("/candidates-screening-status/{screeningStatus}")
    public ResponseEntity<CandidateVenueJobPaginationDto> getCandidateByScreeningStatus(@PathVariable String screeningStatus,@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findCandidateVenueJobByScreeningStatus(screeningStatus,PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERVIEWER') OR hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR')")
    @GetMapping("/count-by-venue/{venueId}")
    public ResponseEntity<DashboardDto> getDashboardStatisticByVenue(@PathVariable Long venueId) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.dashboardStatisticByVenue(venueId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR')")
    @GetMapping("/count-by-all-venue")
    public ResponseEntity<DashboardDto> getDashboardStatisticByAllVenue() throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.dashboardStatisticByAllVenue(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<CandidateVenueJobPaginationDto> getFilter(@RequestParam(required = false) Long venueId,@RequestParam(required = false) String screeningStatus,@RequestParam String sortOrder,@RequestParam String sortBy,@RequestParam Integer pageNumber,@RequestParam Integer pageSize,@RequestParam(required = false) String lastName,@RequestParam(required = false) String level) throws CandidateVenueJobNotFoundException{
        return new ResponseEntity<>(candidateVenueJobService.findListOfCandidatesByFilters(venueId,screeningStatus,sortOrder,sortBy,pageNumber,pageSize,lastName,level), HttpStatus.OK);
    }
}
