package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.service.JobService;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import com.elca.jobfairmanagementsystem.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/venue-job")
public class VenueJobController {

    private final VenueService venueService;
    private final JobService jobService;
    private final VenueJobService venueJobService;

    public VenueJobController(VenueService venueService,JobService jobService,VenueJobService venueJobService) {
        this.venueService = venueService;
        this.jobService = jobService;
        this.venueJobService = venueJobService;
    }

    @GetMapping("/venue/getAllVenue")
    public ResponseEntity<List<VenueDto>> getAllVenues() {
        return new ResponseEntity<>(venueService.searchAllVenue(), HttpStatus.FOUND);
    }

    @GetMapping("/venue/getVenueById/{venueId}")
    public ResponseEntity<VenueDto> getVenueById(@PathVariable Long venueId){
        return new ResponseEntity<>(venueService.searchVenueById(venueId),HttpStatus.FOUND);
    }

    @PostMapping("/venue/save-venue")
    public ResponseEntity saveVenue(@RequestBody VenueDto venueDto){
        venueService.saveVenue(venueDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/venue/update-venue/{venueId}")
    public ResponseEntity updateVenue(@RequestBody VenueDto venueDto){
        venueService.updateVenue(venueDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/venue/delete-venue/{venueId}")
    public ResponseEntity deleteVenue(@PathVariable Long venueId){
        venueService.deleteVenue(venueId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/job/getAllJob")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return new ResponseEntity<>(jobService.searchAllJobs(), HttpStatus.FOUND);
    }

    @GetMapping("/job/getJobById/{jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long jobId){
        return new ResponseEntity<>(jobService.searchJobById(jobId),HttpStatus.FOUND);
    }

    @PostMapping("/job/save-job")
    public ResponseEntity saveJob(@RequestBody JobDto jobDto){
        jobService.saveJob(jobDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/job/update-job/{jobId}")
    public ResponseEntity updateJob(@RequestBody JobDto jobDto){
        jobService.updateJob(jobDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/job/delete-job/{jobId}")
    public ResponseEntity deleteJob(@PathVariable Long jobId){
        jobService.deleteJob(jobId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllVenueJob")
    public ResponseEntity<List<VenueJobDto>> getAllVenueJobs() {
        return new ResponseEntity<>(venueJobService.searchAllVenueJobs(), HttpStatus.FOUND);
    }

    @GetMapping("/getVenueJobById/{venueJobId}")
    public ResponseEntity<VenueJobDto> getVenueJobById(@PathVariable Long venueJobId){
        return new ResponseEntity<>(venueJobService.searchVenueJobById(venueJobId),HttpStatus.FOUND);
    }

    @PostMapping("/save-venue-job")
    public ResponseEntity saveVenueJob(@RequestBody VenueJobDto venueJobDto){
        venueJobService.saveVenueJob(venueJobDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/update-venue-job/{venueJobId}")
    public ResponseEntity updateVenueJob(@RequestBody VenueJobDto venueJobDto){
        venueJobService.updateVenueJob(venueJobDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete-venue-job/{venueJobId}")
    public ResponseEntity deleteVenueJob(@PathVariable Long venueJobId){
        venueJobService.deleteVenueJob(venueJobId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
