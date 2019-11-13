package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/venue-job")
public class VenueJobController {

    private final VenueService venueService;

    public VenueJobController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/get-all-venue")
    public ResponseEntity<List<VenueDto>> getAllVenues() {
        return new ResponseEntity<>(venueService.searchAllVenue(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> saveVenue(@RequestBody VenueDto venueDto){
        venueService.saveVenue(venueDto);
        return new ResponseEntity<>(null , HttpStatus.CREATED);
    }

}
