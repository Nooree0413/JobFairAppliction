package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.dto.VenuePaginationDto;
import com.elca.jobfairmanagementsystem.exception.VenueNotFoundException;
import com.elca.jobfairmanagementsystem.service.VenueService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<VenuePaginationDto> getAllVenues(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws VenueNotFoundException {
        return new ResponseEntity<>(venueService.findAllVenue(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<VenueDto> getVenueById(@PathVariable Long venueId) throws VenueNotFoundException{
        return new ResponseEntity<>(venueService.findVenueById(venueId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveVenue(@RequestBody VenueDto venueDto){
        venueService.saveVenue(venueDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{venueId}")
    public ResponseEntity updateVenue(@RequestBody VenueDto venueDto) throws VenueNotFoundException{
        venueService.updateVenue(venueDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity deleteVenue(@PathVariable Long venueId) throws VenueNotFoundException{
        venueService.deleteVenue(venueId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<List<VenueDto>> getAllVenueByActive(@PathVariable boolean active) throws VenueNotFoundException {
        return new ResponseEntity<>(venueService.findVenueByActive(active), HttpStatus.OK);
    }
}
