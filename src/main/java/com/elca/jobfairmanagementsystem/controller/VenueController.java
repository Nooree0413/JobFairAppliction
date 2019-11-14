package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/venue")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VenueDto>> getAllVenues() {
        return new ResponseEntity<>(venueService.searchAllVenue(), HttpStatus.FOUND);
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<VenueDto> getVenueById(@PathVariable Long venueId){
        return new ResponseEntity<>(venueService.searchVenueById(venueId),HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity saveVenue(@RequestBody VenueDto venueDto){
        venueService.saveVenue(venueDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{venueId}")
    public ResponseEntity updateVenue(@RequestBody VenueDto venueDto){
        venueService.updateVenue(venueDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity deleteVenue(@PathVariable Long venueId){
        venueService.deleteVenue(venueId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
