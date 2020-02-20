package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.dto.VenueJobMultipleSaveDto;
import com.elca.jobfairmanagementsystem.dto.VenueJobPaginationDto;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.VenueJobMapper;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/venue-job")
public class VenueJobController {

    private final VenueJobService venueJobService;
    private final VenueJobMapper venueJobMapper;

    public VenueJobController(VenueJobService venueJobService, VenueJobMapper venueJobMapper) {
        this.venueJobService = venueJobService;
        this.venueJobMapper = venueJobMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VenueJobDto>> getAllVenueJobs() throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findAllVenueJobs(), HttpStatus.OK);
    }

    @GetMapping("/{venueJobId}")
    public ResponseEntity<VenueJobDto> getVenueJobById(@PathVariable Long venueJobId) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findVenueJobById(venueJobId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveVenueJob(@RequestBody VenueJobDto venueJobDto) {
        venueJobService.saveVenueJob(venueJobDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{venueJobId}")
    public ResponseEntity updateVenueJob(@RequestBody VenueJobDto venueJobDto) throws VenueJobNotFoundException {
        venueJobService.updateVenueJob(venueJobDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{venueJobId}")
    public ResponseEntity deleteVenueJob(@PathVariable Long venueJobId) throws VenueJobNotFoundException {
        venueJobService.deleteVenueJob(venueJobId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/jobs")
    public ResponseEntity<VenueJobPaginationDto> getAllJobsByVenue(@RequestParam long venueId, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findByVenueId(venueId, PageRequest.of(pageNumber, pageSize)), HttpStatus.OK);
    }

    @GetMapping("/jobs/category")
    public ResponseEntity<List<VenueJobDto>> getAllJobsByVenueAndCategory(@RequestParam long venueId, @RequestParam String category) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findByVenueIdAndCategory(venueId, category), HttpStatus.OK);
    }

    @GetMapping("/jobs/level")
    public ResponseEntity<List<VenueJobDto>> getAllJobsByLevel(@RequestParam long venueId, @RequestParam String level) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findByLevel(venueId, level), HttpStatus.OK);
    }

    @GetMapping("/jobs/title")
    public ResponseEntity<List<VenueJobDto>> getAllJobsByTitle(@RequestParam long venueId, @RequestParam String title) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findByTitle(venueId, title), HttpStatus.OK);
    }

    @GetMapping("/jobs/category/title")
    public ResponseEntity<List<VenueJobDto>> getAllJobsByTitleAndCategory(@RequestParam long venueId, @RequestParam String title, @RequestParam String category) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findByTitleAndCategory(venueId, title, category), HttpStatus.OK);
    }

    @GetMapping("/jobs/category/level")
    public ResponseEntity<List<VenueJobDto>> getAllJobsByTitleAndLevel(@RequestParam long venueId, @RequestParam String level, @RequestParam String category) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findByLevelAndCategory(venueId, level, category), HttpStatus.OK);
    }

    @GetMapping("/job/venue")
    public ResponseEntity<VenueJobDto> getJobByVenueIdAndJobId(@RequestParam long venueId, @RequestParam long jobId) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobMapper.venueJobEntityToDto(venueJobService.findByVenueIdAndJobId(venueId, jobId)), HttpStatus.OK);
    }

    @PostMapping("/multiple-job")
    public ResponseEntity saveMultipleVenueJob(@RequestBody VenueJobMultipleSaveDto venueJobMultipleSaveDto) throws VenueJobNotFoundException {
        venueJobService.saveMultipleVenueJobs(venueJobMultipleSaveDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/multiple-job/delete/{venueId}/{jobId}")
    public ResponseEntity deleteVenueJob(@PathVariable Long venueId, @PathVariable Long jobId) throws VenueJobNotFoundException {
        venueJobService.deleteVenueJobByJobIdAndVenueId(venueId, jobId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<VenueJobPaginationDto> getFilter(@RequestParam(required = false) String title, @RequestParam String position, @RequestParam String category, @RequestParam(required = false) long venueId, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findListOfJobs(title, position, category, venueId, pageNumber, pageSize), HttpStatus.OK);
    }
}
