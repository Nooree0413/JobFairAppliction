package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/venue-job")
public class VenueJobController {

    private final VenueJobService venueJobService;

    public VenueJobController(VenueJobService venueJobService) {
        this.venueJobService = venueJobService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VenueJobDto>> getAllVenueJobs() throws VenueJobNotFoundException {
        return new ResponseEntity<>(venueJobService.findAllVenueJobs(), HttpStatus.OK);
    }

    @GetMapping("/{venueJobId}")
    public ResponseEntity<VenueJobDto> getVenueJobById(@PathVariable Long venueJobId) throws VenueJobNotFoundException{
        return new ResponseEntity<>(venueJobService.findVenueJobById(venueJobId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveVenueJob(@RequestBody VenueJobDto venueJobDto){
        venueJobService.saveVenueJob(venueJobDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{venueJobId}")
    public ResponseEntity updateVenueJob(@RequestBody VenueJobDto venueJobDto) throws VenueJobNotFoundException{
        venueJobService.updateVenueJob(venueJobDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{venueJobId}")
    public ResponseEntity deleteVenueJob(@PathVariable Long venueJobId) throws VenueJobNotFoundException{
        venueJobService.deleteVenueJob(venueJobId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
