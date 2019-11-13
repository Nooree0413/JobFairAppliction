package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.VenueDto;

import java.util.List;


public interface VenueService {
    void saveVenue(VenueDto venueDto);
    VenueDto searchVenueById (Long venueId);
    List<VenueDto> searchAllVenue();
    void updateVenue(VenueDto venueDto);
    void deleteVenue(Long venueId);
}
